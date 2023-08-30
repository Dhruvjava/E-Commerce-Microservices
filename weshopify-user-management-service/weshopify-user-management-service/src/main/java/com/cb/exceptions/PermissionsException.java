package com.cb.exceptions;

public class PermissionsException extends RuntimeException {

    private String code;

    private String errorMessage;

    public PermissionsException(String code, String errorMessage, String message) {
        super(message);
        this.code = code;
        this.errorMessage = errorMessage;
    }
}
