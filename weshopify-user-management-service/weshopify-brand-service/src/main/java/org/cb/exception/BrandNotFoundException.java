package org.cb.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.commons.base.rs.ErrorRs;

import java.io.Serial;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BrandNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 6876016682983789660L;

    private String code;

    private String message;

    private List<ErrorRs> errors;

    public BrandNotFoundException(String code, String message) {
        this.code = code;
        this.message = message;
    }

//    public BrandNotFoundException(String code, String message, List<ErrorRs> errors) {
//        this.code = code;
//        this.message = message;
//        this.errors = errors;
//    }
}
