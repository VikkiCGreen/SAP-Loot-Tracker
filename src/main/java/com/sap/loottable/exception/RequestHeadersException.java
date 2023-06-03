package com.sap.loottable.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class RequestHeadersException extends AbstractRuntimeException {

    private final List<String> errors;

    public RequestHeadersException(List<String> errors) {
        super(errors.toString());
        this.errors = errors;
    }
}
