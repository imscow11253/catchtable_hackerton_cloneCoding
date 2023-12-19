package com.example.demo.response.status;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BaseExceptionResponseStatus implements ResponseStatus{

    SUCCESS(1000, HttpStatus.OK.value(), "요청에 성공하였습니다."),

    NO_EXIST_RESTAURANT_ID(3001, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 식당입니다."),
    INVALID_WEEK_VALUE(3002, HttpStatus.BAD_REQUEST.value(), "week 형식이 잘못되었습니다."),

    INVALID_MEMBER_VALUE(4001, HttpStatus.BAD_REQUEST.value(), "잘못된 데이터 형식입니다."),
    DUPLICATE_MEMBER_IDENTIFIER(4002, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 아이디입니다."),
    DUPLICATED_MEMBER_PHONENUMBER(4002, HttpStatus.BAD_REQUEST.value(),"이미 존재하는 핸드폰 번호입니다."),
    INVALID_MEMBER_TYPE(4003, HttpStatus.BAD_REQUEST.value(), "분류되지 않은 member tpye 입니다."),
    NO_EXIST_IDENTIFIER(4004, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 아이디입니다."),
    NO_EXIST_PASSWORD(4005, HttpStatus.BAD_REQUEST.value(), "비밀번호가 틀렸습니다."),

    JWT_ERROR(5000, HttpStatus.UNAUTHORIZED.value(), "JWT에서 오류가 발생하였습니다."),
    TOKEN_NOT_FOUND(5001, HttpStatus.BAD_REQUEST.value(), "토큰이 HTTP Header에 없습니다."),
    UNSUPPORTED_TOKEN_TYPE(5002, HttpStatus.BAD_REQUEST.value(), "지원되지 않는 토큰 형식입니다."),
    INVALID_TOKEN(5003, HttpStatus.UNAUTHORIZED.value(), "유효하지 않은 토큰입니다."),
    MALFORMED_TOKEN(5004, HttpStatus.UNAUTHORIZED.value(), "토큰이 올바르게 구성되지 않았습니다."),
    EXPIRED_TOKEN(5005, HttpStatus.UNAUTHORIZED.value(), "만료된 토큰입니다."),
    TOKEN_MISMATCH(5006, HttpStatus.UNAUTHORIZED.value(), "로그인 정보가 토큰 정보와 일치하지 않습니다.");


    private final int code;
    private final int status;
    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
