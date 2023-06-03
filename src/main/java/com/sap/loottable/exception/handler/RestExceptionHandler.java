package com.sap.loottable.exception.handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.MimeType;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.sap.loottable.exception.BadDataException;
import com.sap.loottable.exception.Error;
import com.sap.loottable.exception.RequestHeadersException;
import com.sap.loottable.exception.RequestParametersException;
import com.sap.loottable.exception.TimeoutException;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    Environment env;

    @ExceptionHandler
    public ResponseEntity<Object> handleOtherExceptions(Exception ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        logStackTrace(ex);
        return getErrorResponseProdNonProd(errors, "an unexpected error occurred", getDebugMessage(ex),
            HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        logStackTrace(ex);
        return getErrorResponseProdNonProd(errors, "error in provided request", getDebugMessage(ex),
            HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        logStackTrace(ex);
        return getErrorResponseProdNonProd(errors, "Runtime Exception", getDebugMessage(ex),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getReason());
        logStackTrace(ex);
        return getErrorResponseProdNonProd(errors, "Response Status Exception", getDebugMessage(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BadDataException.class})
    public ResponseEntity<Object> handleBadDataException(BadDataException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        logStackTrace(ex);
        return getErrorResponseProdNonProd(errors, "Bad Data Exception", getDebugMessage(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TimeoutException.class})
    public ResponseEntity<Object> handleTimeoutException(TimeoutException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        logStackTrace(ex);
        return getErrorResponseProdNonProd(errors, "Timeout Exception", getDebugMessage(ex),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {RequestParametersException.class})
    public ResponseEntity<Object> handleRequestParametersException(RequestParametersException ex) {
        List<String> errors = new ArrayList<>(ex.getErrors());
        logStackTrace(ex);
        return getErrorResponseProdNonProd(errors, "Request Parameters Exception", getDebugMessage(ex),
            HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestHeadersException.class)
    public ResponseEntity<Object> handleRequestHeaders(RequestHeadersException requestHeadersException) {
        List<String> errors = new ArrayList<>(requestHeadersException.getErrors());
        return getErrorResponseProdNonProd(errors, "Request Headers Exception",
            getDebugMessage(requestHeadersException), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        logStackTrace(ex);
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        return getErrorResponseProdNonProd(errors, "Method Arguments Not Valid", getDebugMessage(ex),
            HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
        HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        logStackTrace(ex);
        errors.add(ex.getParameterName() + " is missing");
        return getErrorResponseProdNonProd(errors, "missing servlet request parameter", getDebugMessage(ex),
            HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
        HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        logStackTrace(ex);
        errors.add(buildError(ex));
        return getErrorResponseProdNonProd(errors, "http message not readable", getDebugMessage(ex),
            HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
        HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        logStackTrace(ex);
        String supportedTypes =
            ex.getSupportedMediaTypes().stream().map(MimeType::getType).collect(Collectors.joining(","));
        errors.add("media type is not one of supported types: " + supportedTypes);
        return getErrorResponseProdNonProd(errors, "http media type not supported", getDebugMessage(ex),
            HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
        HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        logStackTrace(ex);
        String supportedTypes =
            ex.getSupportedMediaTypes().stream().map(MimeType::getType).collect(Collectors.joining(","));
        errors.add("media type is not one of supported types: " + supportedTypes);
        return getErrorResponseProdNonProd(errors, "http media type not acceptable", getDebugMessage(ex),
            HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
        HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getVariableName() + " is missing");
        logStackTrace(ex);
        return getErrorResponseProdNonProd(errors, "missing path variable", getDebugMessage(ex),
            HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
        HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        logStackTrace(ex);
        return getErrorResponseProdNonProd(errors, "servlet request binding exception", getDebugMessage(ex),
            HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> getErrorResponseProdNonProd(List<String> errors, String errorMessage,
        String debugMessage, HttpStatus status) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Error errorResponseWithStackTrace = Error.builder().status(status).message(errorMessage)
            .debugMessage(debugMessage).timestamp(localDateTime).errors(errors).build();
        return new ResponseEntity<>(errorResponseWithStackTrace, errorResponseWithStackTrace.getStatus());
    }

    private void logStackTrace(Exception e) {
        log.error("Error occurred in Loot Request: {}", e.getMessage(), e);
    }

    private String getDebugMessage(Exception e) {
        if (env != null && Arrays.asList(env.getActiveProfiles()).contains("dev")) {
            return e.getMessage();
        }
        return null;
    }

    // Used to build the error message based on the profiles
    private String buildError(NestedRuntimeException ex) {
        String exceptionMessage = ex.getMostSpecificCause().getMessage();
        if (ex.getCause() instanceof JsonMappingException) {
            List<JsonMappingException.Reference> paths = ((JsonMappingException) ex.getCause()).getPath();
            String invalidField =
                paths.stream().map(path -> Opt.orElse(path.getFieldName(), "[" + path.getIndex() + "]"))
                    .collect(Collectors.joining("."));
            return invalidField + ": invalid value";
        }
        if (StringUtils.isNotEmpty(exceptionMessage) && exceptionMessage.contains("Cannot deserialize value of type")) {
            if (env != null && (Arrays.asList(env.getActiveProfiles()).contains("prod"))) {
                //TODO: stuff
            } else {
                String[] strArr = StringUtils.split(exceptionMessage, "\n");
                exceptionMessage = strArr != null ? strArr[0] : exceptionMessage;
            }
        }
        return exceptionMessage;
    }
}
