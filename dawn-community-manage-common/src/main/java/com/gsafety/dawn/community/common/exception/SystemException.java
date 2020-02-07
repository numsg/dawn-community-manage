package com.gsafety.dawn.community.common.exception;

/**
 * 自定义通用系统异常,表示服务端内部出现的相关异常.
 *
 * @author yezhiyang
 * @date 2018 /8/22
 */
public class SystemException extends RuntimeException {
    private int code; //NOSONAR

    /**
     * Instantiates a new System exception.
     *
     * @param status the status
     */
    public SystemException(ErrorCode status) {
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
