package com.cb.handler.advice;

import com.cb.exception.NotificationException;
import com.cb.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class NotificationAdvice {

    @ExceptionHandler(NotificationException.class)
    public ResponseEntity<ProblemDetail> handleNotificationException(NotificationException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getCode());
        problemDetail.setDetail(e.getMessage());
        Optional.ofNullable(e.getErrors()).filter(Utils::isNotEmpty).ifPresent(err -> {
            problemDetail.setProperty("errors", err);
        });
        return ResponseEntity.badRequest().body(problemDetail);
    }

}
