package org.cb.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.commons.base.rs.ErrorRs;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InvalidCategoryException extends RuntimeException {

    private String code;

    private String message;

    private List<ErrorRs> errors;

    public InvalidCategoryException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
