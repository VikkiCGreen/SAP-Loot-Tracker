package com.sap.loottable.exception;

import lombok.Getter;

import java.util.List;

import org.springframework.http.HttpStatus;

@Getter
public class RequestParametersException extends AbstractRuntimeException {

    private List<String> errors;

    public RequestParametersException(String message) {
        super(message);
    }

    public RequestParametersException(List<String> errors) {
        super(errors.toString());
        this.errors = errors;
    }

    public RequestParametersException(Throwable cause) {
        super(cause);
    }

    public RequestParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
