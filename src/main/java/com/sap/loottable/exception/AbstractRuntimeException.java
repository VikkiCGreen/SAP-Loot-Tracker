package com.sap.loottable.exception;

public abstract class AbstractRuntimeException extends RuntimeException {

    protected AbstractRuntimeException(String message) {
        super(message);
    }

    protected AbstractRuntimeException(Throwable cause) {
        super(cause);
    }

    protected AbstractRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
