package com.cb.exceptions;

import com.cb.base.rs.ErrorRs;

import java.util.List;

public class UsersException extends RuntimeException {

    private String code;

    private String errorMessage;

    private List<ErrorRs> errors;

    public UsersException(String code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public UsersException(String code, String errorMessage, List<ErrorRs> errors) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

}
