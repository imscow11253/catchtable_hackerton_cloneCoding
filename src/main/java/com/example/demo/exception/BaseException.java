package com.example.demo.exception;

import com.example.demo.dto.base.ResponseStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class BaseException extends RuntimeException{
    public final ResponseStatus exceptionStatus;

    public BaseException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

    public BaseException(ResponseStatus exceptionStatus, String message) {
        super(message);
        this.exceptionStatus = exceptionStatus;
    }
}
