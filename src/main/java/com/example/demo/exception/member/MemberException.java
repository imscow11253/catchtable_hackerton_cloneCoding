package com.example.demo.exception.member;

import com.example.demo.response.status.ResponseStatus;
import org.apache.coyote.Response;

public class MemberException extends RuntimeException{
    private final ResponseStatus exceptionStatus;

    public MemberException(ResponseStatus exceptionStatus){
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

    public MemberException(ResponseStatus exceptionStatus, String message) {
        super(message);
        this.exceptionStatus = exceptionStatus;
    }
}
