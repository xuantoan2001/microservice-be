package com.xuantoan.springboot.exception;

import com.xuantoan.springboot.controller.output.CustomReponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class APIExceptionHandLer {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomReponse handlerNotFoundException(NotFoundException ex, WebRequest req) {
        return new CustomReponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomReponse handlerSQLException(SQLException ex, WebRequest req) {
        return new CustomReponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
