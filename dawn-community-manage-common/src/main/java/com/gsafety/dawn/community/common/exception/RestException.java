package com.gsafety.dawn.community.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by numsg on 2016/10/25.
 * Define RestException Abstract class
 */
public abstract class RestException  extends RuntimeException{

    /**
     * The Http status.
     */
    protected final HttpStatus httpStatus;
    /**
     * The Message.
     */
    protected final String message;

    /**
     * Instantiates a new Rest exception.
     *
     * @param httpStatus the http status
     * @param message    the message
     */
    public RestException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    /**
     * Gets http status.
     *
     * @return the http status
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
