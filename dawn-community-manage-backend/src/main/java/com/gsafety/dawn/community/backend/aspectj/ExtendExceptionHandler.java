package com.gsafety.dawn.community.backend.aspectj;


import com.gsafety.dawn.community.common.exception.HttpError;
import com.gsafety.dawn.community.common.exception.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by gaoqiang on 2016/10/27.
 * Extend the Exception Handler
 */

@RestController
@ControllerAdvice
public class ExtendExceptionHandler {

    /**
     * The Logger.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpServletResponse response;

    /**
     * Handle self defined exceptions http error.
     *
     * @param restException the rest exception
     * @return the http error
     */
    @ExceptionHandler(RestException.class)
    public HttpError handleSelfDefinedExceptions(RestException restException){
        logger.error(restException.getMessage(), restException);
        response.setStatus(restException.getHttpStatus().value());
        return new HttpError(restException.getHttpStatus().value(),
                restException.getMessage());
    }

    /**
     * Handle un catch exceptions http error.
     *
     * @param exception the exception
     * @return the http error
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpError handleUnCatchExceptions(Exception exception){
        logger.error(exception.getMessage(), exception);
        return new HttpError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage());
    }
}
