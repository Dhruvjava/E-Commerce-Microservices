package org.cb.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BrandException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5745180189082126828L;

    private String code;

    private String message;
}
