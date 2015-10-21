package com.pan.bbf.common.Exception;

/**
 *
 * root for all user-defined exceptions
 *
 */
public class BffException extends RuntimeException {

    private static final long serialVersionUID = 20140804L;

    public BffException() {
    }

    public BffException(String message) {
        super(message);
    }

    public BffException(String message, Throwable cause) {
        super(message, cause);
    }

    public BffException(Throwable cause) {
        super(cause);
    }
}
