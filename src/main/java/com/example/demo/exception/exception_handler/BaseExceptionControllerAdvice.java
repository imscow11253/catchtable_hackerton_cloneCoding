package com.example.demo.exception.exception_handler;

import com.example.demo.dto.base.BaseExceptionResponse;
import com.example.demo.dto.base.BaseExceptionResponseStatus;
import com.example.demo.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class BaseExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseException.class)
    public BaseExceptionResponse handle_BadRequest(BaseException e) {

        return new BaseExceptionResponse(e.exceptionStatus);
    }
}
