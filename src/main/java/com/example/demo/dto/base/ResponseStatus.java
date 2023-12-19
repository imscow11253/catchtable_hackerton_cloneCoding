package com.example.demo.dto.base;

public interface ResponseStatus {

    int getCode();

    boolean getIsSuccess();

    String getMessage();

}