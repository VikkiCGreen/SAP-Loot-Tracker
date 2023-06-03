package com.sap.loottable.exception;

import org.springframework.http.HttpStatus;

public class BadDataException extends AbstractRuntimeException {

    public BadDataException(String message) {
        super(message);
    }

    public BadDataException(Throwable cause) {
        super(cause);
    }

    public BadDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getCode() {
        return HttpStatus.BAD_REQUEST;
    }

}
