package com.cb.exceptions;

import org.cb.commons.base.rs.ErrorRs;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class UsersDuplicateFieldVoilationException extends RuntimeException {

    private String code;

    private String errorMessage;

    private List<ErrorRs> errors;

    public UsersDuplicateFieldVoilationException(String code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public UsersDuplicateFieldVoilationException(String code, String errorMessage, List<ErrorRs> errors) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

}
