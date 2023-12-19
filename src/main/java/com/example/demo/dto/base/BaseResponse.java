package com.example.demo.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.lang.annotation.Annotation;
import static com.example.demo.dto.base.BaseExceptionResponseStatus.SUCCESS;

@Getter
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseResponse<T> implements ResponseStatus {

    private final int code;
    private final boolean isSuccess;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;

    public BaseResponse(T result) {
        this.code = SUCCESS.getCode();
        this.isSuccess = SUCCESS.getIsSuccess();
        this.message = SUCCESS.getMessage();
        this.result = result;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public boolean getIsSuccess() {
        return isSuccess;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
