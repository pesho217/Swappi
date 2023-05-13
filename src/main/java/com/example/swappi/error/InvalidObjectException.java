package com.example.swappi.error;

import lombok.Getter;

import java.util.Map;

@Getter
public class InvalidObjectException extends PersonBaseException {

    private final Map<String, String> errors;

    public InvalidObjectException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
}
