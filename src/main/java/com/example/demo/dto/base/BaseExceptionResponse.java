package com.example.demo.dto.base;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})

public class BaseExceptionResponse implements ResponseStatus {

    private final int code;
    private final boolean isSuccess;
    private final String message;

    public BaseExceptionResponse(ResponseStatus status) {
        this.code = status.getCode();
        this.isSuccess = status.getIsSuccess();
        this.message = status.getMessage();
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public boolean getIsSuccess() {
        return this.isSuccess;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
