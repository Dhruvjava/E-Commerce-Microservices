package com.cb.exceptions;

public class RolesNotFoundException extends RuntimeException {

    private String code;

    private String errorMessage;

    public RolesNotFoundException(String code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

}
