package com.cb.exception;

import com.cb.base.rs.ErrorRs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationException extends RuntimeException{

    private String code;

    private String message;

    private List<ErrorRs> errors;

    public NotificationException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
