package com.sap.loottable.exception;

import org.springframework.http.HttpStatus;

public class TimeoutException extends AbstractRuntimeException {

    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(Throwable cause) {
        super(cause);
    }

    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getCode() {
        return HttpStatus.REQUEST_TIMEOUT;
    }
}
