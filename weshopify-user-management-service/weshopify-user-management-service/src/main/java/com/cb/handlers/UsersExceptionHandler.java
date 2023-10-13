package com.cb.handlers;

import com.cb.base.rs.ErrorRs;
import com.cb.exceptions.*;
import com.cb.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
@Slf4j
public class UsersExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsersException.class)
    public ResponseEntity<ProblemDetail> handleUsersException(UsersException pe) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(pe.getCode());
        problemDetail.setDetail(pe.getErrorMessage());
        if (Utils.isNotEmpty(pe.getErrors())) {
            problemDetail.setProperty("errors", pe.getErrors());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(problemDetail);
    }
    @ExceptionHandler(UsersNotFoundException.class)
    public ResponseEntity<ProblemDetail> handlePermissionsNotFoundException(UsersNotFoundException pe) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(pe.getCode());
        problemDetail.setDetail(pe.getErrorMessage());
//        if (Utils.isNotEmpty(pe.getErrors())) {
//            problemDetail.setProperty("errors", pe.getErrors());
//        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(problemDetail);
    }

    @ExceptionHandler(UsersDuplicateFieldVoilationException.class)
    public ResponseEntity<ProblemDetail> handleDataIntegrityViolationException(UsersDuplicateFieldVoilationException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(ex.getCode());
        problemDetail.setDetail(ex.getErrorMessage());
        problemDetail.setProperty("errors", ex.getErrors());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }
}
