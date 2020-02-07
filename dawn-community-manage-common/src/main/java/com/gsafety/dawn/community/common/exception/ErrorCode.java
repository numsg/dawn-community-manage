package com.gsafety.dawn.community.common.exception;

/**
 * 统一的自定义异常返回状态枚举类,包含自定义错误码和自定义错误消息.
 * 错误码定义规则:A-BB-CC, A为1代表系统错误, 为2代表业务错误, 3为意想不到的异常;
 * BB代表各个服务模块(或子系统代号); CC代表错误序号,按顺序递增.
 */
public enum ErrorCode {

    // 系统相关错误
    /**
     * 对于一些服务端事先预料到的异常,进行捕获时可以记录日志,然后抛出SystemException.
     */
    SYSTEM_INNER_ERROR(10001, "The system is busy, please try again later!"),
    /**
     * 一般指调用远程服务接口时发生的异常.
     */
    CALL_REMOTE_SERVICE_ERROR(10002, "Remote service call failed!"),
    /**
     * 远程接口不存在
     */
    REMOTE_INTERFACE_NOT_EXIST(10003, "Remote Interface does not exist!"),

    // 未知错误
    /**
     * 一般在系统发生意想不到的异常时返回给调用者
     */
    UNEXPECTED_ERROR(30001, "An unexpected internal server error occurred!"),


    // 业务相关错误

    // 校验相关
    /**
     * 参数校验错误,一般是存数据的时候Hibernate Validator校验不通过.
     */
    PARAM_IS_INVALID(20001, "Parameter verification failed!"),
    /**
     * 一般指传递的参数通过StringUtils.isBlank()方法判断为真时可依情况抛出此错误.
     */
    PARAM_IS_BLANK(20002, "Parameter is blank!"),
    /**
     * 一般指SpingMVC进行参数绑定时类型匹配异常.
     */
    PARAM_TYPE_ERROR(20003, "Incorrect parameter type!"),
    /**
     * 一般指调用者传递的参数由SpringMVC进行对象绑定后,对象中某些成员变量值缺少.
     */
    PARAM_NOT_COMPLETE(20004, "Incomplete parameter!"),

    // 数据相关
    /**
     * 一般指根据某个字段查找单条记录时,该记录不存在.
     */
    DATA_NOT_EXIST(20005, "Data does not exist!"),
    /**
     * 一般指查找多条数据时,未找到任何数据.
     */
    DATA_NOT_FOUND(20006, "Data not found!"),
    /**
     * 一般指插入数据时,该记录已存在.
     */
    DATA_ALREADY_EXISTS(20007, "Data already exists!");


    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
