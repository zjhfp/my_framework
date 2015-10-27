package com.pan.bbf.common.exception;

/**
 *
 * root for all user-defined exceptions
 *
 */
public class BBFException extends RuntimeException {

    private static final long serialVersionUID = 20140804L;

    public BBFException() {
    }

    public BBFException(String message) {
        super(message);
    }

    public BBFException(String message, Throwable cause) {
        super(message, cause);
    }

    public BBFException(Throwable cause) {
        super(cause);
    }
}
