package com.example.demo.dto.base;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BaseExceptionResponseStatus implements ResponseStatus {

    /**
     * 1000: 요청 성공 (OK)
     */
    SUCCESS(1000, true, "요청에 성공하였습니다."),

    /**
     * 2000: Request 오류 (BAD_REQUEST)
     */
//    BAD_REQUEST(2000, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 요청입니다."),
//    URL_NOT_FOUND(2001, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 URL 입니다."),
//    METHOD_NOT_ALLOWED(2002, HttpStatus.METHOD_NOT_ALLOWED.value(), "해당 URL에서는 지원하지 않는 HTTP Method 입니다."),

    /**
     * 3000: Reservation 오류
     */
//    SERVER_ERROR(3000, false, "서버에서 오류가 발생하였습니다."),
//    DATABASE_ERROR(3001, false, "데이터베이스에서 오류가 발생하였습니다."),
//    BAD_SQL_GRAMMAR(3002, false, "SQL에 오류가 있습니다.");
    BAD_QS_RESTAURANT_ID_RESERVATION_ERROR(3003, false, "restaurant_id값이 유요하지 않습니다."),
    BAD_QS_DAY_RESERVATION_ERROR(3004, false, "day 파라미터가 유효하지 않습니다.");

    private final int code;
    private final boolean isSuccess;
    private final String message;

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
