package com.example.demo.exception.reservation;

import com.example.demo.dao.ReservationDao;
import com.example.demo.response.status.ResponseStatus;

public class ReservationException extends RuntimeException{
    private final ResponseStatus exceptionStatus;

    public ReservationException(ResponseStatus exceptionStatus){
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

    public ReservationException(ResponseStatus exceptionStatus, String message) {
        super(message);
        this.exceptionStatus = exceptionStatus;
    }

}
