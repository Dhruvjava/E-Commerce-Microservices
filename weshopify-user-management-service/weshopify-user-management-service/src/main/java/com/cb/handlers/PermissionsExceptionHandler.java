package com.cb.handlers;

import com.cb.exceptions.PermissionsException;
import com.cb.exceptions.PermissionsNotFoundException;
import com.cb.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class PermissionsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PermissionsException.class)
    public ResponseEntity<ProblemDetail> handlePermissionsException(PermissionsException pe) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(pe.getCode());
        problemDetail.setDetail(pe.getErrorMessage());
        if (Utils.isNotEmpty(pe.getErrors())) {
            problemDetail.setProperty("errors", pe.getErrors());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(problemDetail);
    }
    @ExceptionHandler(PermissionsNotFoundException.class)
    public ResponseEntity<ProblemDetail> handlePermissionsNotFoundException(PermissionsNotFoundException pe) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(pe.getCode());
        problemDetail.setDetail(pe.getErrorMessage());
//        if (Utils.isNotEmpty(pe.getErrors())) {
//            problemDetail.setProperty("errors", pe.getErrors());
//        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(problemDetail);
    }
}
