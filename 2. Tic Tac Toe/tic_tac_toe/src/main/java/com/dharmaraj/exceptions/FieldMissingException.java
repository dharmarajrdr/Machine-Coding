package com.dharmaraj.exceptions;

public class FieldMissingException extends Exception {

    public FieldMissingException(String fieldName) {
        super("Field '" + fieldName + "' is missing.");
    }
}
