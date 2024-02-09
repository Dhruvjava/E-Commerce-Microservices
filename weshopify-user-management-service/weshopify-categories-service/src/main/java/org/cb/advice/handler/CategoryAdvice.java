package org.cb.advice.handler;

import org.cb.exception.InvalidCategoryException;
import org.cb.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class CategoryAdvice {

    @ExceptionHandler(InvalidCategoryException.class)
    public ResponseEntity<ProblemDetail> handleInvalidCategoryException(
                    InvalidCategoryException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(ex.getCode());
        problemDetail.setDetail(ex.getMessage());
        Optional.of(ex.getErrors()).filter(Utils::isNotEmpty).ifPresent(err -> problemDetail.setProperty("errors", err));
        return ResponseEntity.badRequest().body(problemDetail);
    }

}
