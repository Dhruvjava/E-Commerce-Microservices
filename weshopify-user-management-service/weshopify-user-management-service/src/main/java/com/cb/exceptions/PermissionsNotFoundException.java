package com.cb.exceptions;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class PermissionsNotFoundException extends RuntimeException {

    private String code;

    private String errorMessage;

    public PermissionsNotFoundException(String code, String errorMessage, String message) {
        super(message);
        this.code = code;
        this.errorMessage = errorMessage;
    }
}
