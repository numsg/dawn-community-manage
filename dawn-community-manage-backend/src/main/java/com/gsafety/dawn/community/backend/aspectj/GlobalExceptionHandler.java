package com.gsafety.dawn.community.backend.aspectj;

import com.gsafety.dawn.community.common.exception.BusinessException;
import com.gsafety.dawn.community.common.exception.ErrorCode;
import com.gsafety.dawn.community.common.exception.SystemException;
import com.gsafety.java.common.exception.HttpError;
import com.gsafety.java.common.exception.RestException;
import com.gsafety.java.common.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * The type Global exception handler.
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * The Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private HttpServletResponse response;

    /**
     * 用来统一处理自定义业务异常、SpringMVC参数绑定类型匹配异常、
     * http请求参数转换异常、spring data删除数据不存在异常,
     * RestTemplate远程调用异常,
     * 返回状态码为400,返回数据包含自定义errorCode和message.
     *
     * @param ex 异常对象，该异常一般是API调用者造成的.
     * @return HttpError http error
     */
    @ExceptionHandler({BusinessException.class, TypeMismatchException.class,
            HttpMessageNotReadableException.class, EmptyResultDataAccessException.class,
            HttpClientErrorException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpError handleBadRequest(final Exception ex) {
        logger.warn(ex.getMessage(), ex);

        // 例如发生参数类型转换异常时会进入此分支
        if (ex instanceof TypeMismatchException) {
            return new HttpError(ErrorCode.PARAM_TYPE_ERROR.getCode(), ErrorCode.PARAM_TYPE_ERROR.getMessage());
        }

        // 例如发生json参数解析异常时会进入此分支
        if (ex instanceof HttpMessageNotReadableException) {
            return new HttpError(ErrorCode.PARAM_IS_INVALID.getCode(), ErrorCode.PARAM_IS_INVALID.getMessage());
        }

        // 例如删除时指定ID的记录不存在
        if (ex instanceof EmptyResultDataAccessException) {
            return new HttpError(ErrorCode.DATA_NOT_EXIST.getCode(), ErrorCode.DATA_NOT_EXIST.getMessage());
        }

        // 处理RestTemplate调用远程服务4xx错误
        if (ex instanceof HttpClientErrorException) {
            return handleRestTemplateError((HttpStatusCodeException) ex,
                    new HttpError(ErrorCode.PARAM_IS_INVALID.getCode(), ErrorCode.PARAM_IS_INVALID.getMessage()));
        }

        return new HttpError(((BusinessException) ex).getCode(), ex.getMessage());
    }

    /**
     * 用来统一处理已知服务内部异常,返回状态码为500,返回数据包含自定义errorCode和message.
     *
     * @param ex 自定义系统异常,该异常一般是服务端内部发生错误.
     * @return HttpError http error
     */
    @ExceptionHandler({SystemException.class, HttpServerErrorException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpError handleInternalServerError(final Exception ex) {
        logger.error(ex.getMessage(), ex);

        // 处理RestTemplate调用远程服务5xx错误
        if (ex instanceof HttpServerErrorException) {
            return handleRestTemplateError((HttpStatusCodeException) ex,
                    new HttpError(ErrorCode.CALL_REMOTE_SERVICE_ERROR.getCode(), ErrorCode.CALL_REMOTE_SERVICE_ERROR.getMessage()));
        }

        return new HttpError(((SystemException)ex).getCode(), ex.getMessage());
    }

    /**
     * Handle self defined exceptions http error.
     *
     * @param restException the rest exception
     * @return the http error
     */
    @ExceptionHandler(RestException.class)
    public HttpError handleSelfDefinedExceptions(RestException restException) {
        logger.error(restException.getMessage(), restException);
        response.setStatus(restException.getHttpStatus().value());
        return new HttpError(restException.getHttpStatus().value(), restException.getMessage());
    }

    /**
     * Handle un catch exceptions http error.
     *
     * @param exception the exception
     * @return the http error
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpError handleUnCatchExceptions(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return new HttpError(ErrorCode.UNEXPECTED_ERROR.getCode(), ErrorCode.UNEXPECTED_ERROR.getMessage());
    }

    /**
     * 处理RestTemplate调用异常
     * @param ex HttpStatusCodeException
     * @param httpError HttpError
     * @return HttpError
     */
    private HttpError handleRestTemplateError(HttpStatusCodeException ex, HttpError httpError) {
        String responseBodyAsString = ex.getResponseBodyAsString();
        logger.warn(responseBodyAsString);

        try {
            HashMap hashMap = JsonUtil.fromJson(responseBodyAsString, HashMap.class);
            if (hashMap == null || hashMap.isEmpty()) {
                return httpError;
            }
            if ("404".equals(hashMap.get("status").toString())) {
                return new HttpError(ErrorCode.REMOTE_INTERFACE_NOT_EXIST.getCode(), ErrorCode.REMOTE_INTERFACE_NOT_EXIST.getMessage());
            }
            return new HttpError((int)hashMap.get("errorCode"), (String)hashMap.get("message"));
        } catch (Exception e) {
            throw new RuntimeException(e); //NOSONAR
        }
    }
}

