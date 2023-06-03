package com.sap.loottable.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@Getter
@Setter
public class Error {

    private HttpStatus status;

    private String message;

    @Builder.Default
    private String debugMessage = "";

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timestamp;

    private List<String> errors;

    public Error(HttpStatus status, String message, String debugMessage, LocalDateTime timestamp, List<String> errors) {
        this.status = status;
        this.message = message;
        this.debugMessage = debugMessage;
        this.timestamp = timestamp;
        this.errors = errors;
    }

}
