package com.gsafety.dawn.community.common.exception;


/**
 * Define the http Exception error model
 */
public class HttpError {
    private int errorCode;
    private String message;

    /**
     * Instantiates a new Http error.
     *
     * @param errorCode    the errorCode
     * @param message the message
     */
    public HttpError(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    /**
     * Gets errorCode.
     *
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets errorCode.
     *
     * @param errorCode the errorCode
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
