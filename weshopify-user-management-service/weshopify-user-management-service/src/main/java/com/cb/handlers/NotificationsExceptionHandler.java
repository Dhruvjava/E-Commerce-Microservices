package com.cb.handlers;

import com.cb.exceptions.NotificationsException;
import com.cb.exceptions.UsersDuplicateFieldVoilationException;
import com.cb.exceptions.UsersException;
import com.cb.exceptions.UsersNotFoundException;
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
public class NotificationsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotificationsException.class)
    public ResponseEntity<ProblemDetail> handleUsersException(NotificationsException ne) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(ne.getCode());
        problemDetail.setDetail(ne.getErrorMessage());
        if (Utils.isNotEmpty(ne.getErrors())) {
            problemDetail.setProperty("errors", ne.getErrors());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(problemDetail);
    }

}
