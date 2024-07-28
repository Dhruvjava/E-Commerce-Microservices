package org.cb.advice.handler;

import org.cb.Messages;
import org.cb.category.constants.ErrorCodes;
import org.cb.commons.base.rs.ErrorRs;
import org.cb.exception.CategoryNotFoundException;
import org.cb.exception.InvalidCategoryException;
import org.cb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Optional;

@RestControllerAdvice
public class CategoryAdvice {

    @Autowired
    private Messages messages;

    @ExceptionHandler(InvalidCategoryException.class)
    public ResponseEntity<ProblemDetail> handleInvalidCategoryException(
            InvalidCategoryException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(ex.getCode());
        problemDetail.setDetail(ex.getMessage());
        Optional.of(ex.getErrors()).filter(Utils::isNotEmpty)
                .ifPresent(err -> problemDetail.setProperty("errors", err));
        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleCategoryNotFoundException(
            CategoryNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(ex.getCode());
        problemDetail.setDetail(ex.getMessage());
        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        List<ErrorRs> errors = e.getBindingResult().getAllErrors().stream()
                .map(err -> Utils.populateErrorRSs(err.getDefaultMessage(), messages)).toList();
        problemDetail.setTitle(ErrorCodes.EC_INVALID_INPUT);
        problemDetail.setDetail(messages.getErrorProperties(ErrorCodes.EC_INVALID_INPUT));
        Optional.of(errors).ifPresent(err ->
                problemDetail.setProperty("errors", err)
        );
        return ResponseEntity.badRequest().body(problemDetail);
    }

}
