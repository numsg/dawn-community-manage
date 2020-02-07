package com.gsafety.dawn.community.common.exception;

/**
 * 自定义通用业务异常,表示不符合我们业务规则的相关异常.
 */
public class BusinessException extends RuntimeException {
    private int code; //NOSONAR

    /**
     * Instantiates a new Business exception.
     *
     * @param status the status
     */
    public BusinessException(ErrorCode status) {
        super(status.getMessage());
        this.code = status.getCode();
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
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }
}