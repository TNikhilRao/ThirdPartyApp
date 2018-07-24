package com.thirdparty.util;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class DownstreamException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ErrorResponse errorResponse;

    public DownstreamException(String exceptionMessage, ErrorResponse errorResponse) {
        super(exceptionMessage);
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getDownstreamErrorResponse() {
        return this.errorResponse;
    }

}   