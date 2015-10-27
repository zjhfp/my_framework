package com.pan.bbf.paginator.exception;

public class PageNotSupportException extends RuntimeException {

    private static final long serialVersionUID = 20140804L;

    public PageNotSupportException() {
    }

    public PageNotSupportException(String message) {
        super(message);
    }

    public PageNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageNotSupportException(Throwable cause) {
        super(cause);
    }
}
