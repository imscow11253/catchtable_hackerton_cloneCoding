package com.example.demo.controller;

import com.example.demo.dto.base.BaseExceptionResponse;
import com.example.demo.dto.base.BaseResponse;
import com.example.demo.dto.reservation.ReservationDayResponse;
import com.example.demo.exception.BaseException;
import com.example.demo.service.ReservationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.example.demo.dto.base.BaseExceptionResponseStatus.BAD_QS_DAY_RESERVATION_ERROR;
import static com.example.demo.dto.base.BaseExceptionResponseStatus.BAD_QS_RESTAURANT_ID_RESERVATION_ERROR;

@Slf4j
@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/day")
    public BaseResponse<ReservationDayResponse> reserveDay(
            @RequestParam(required = false) String restaurant_id,
            @RequestParam(required = false) String day) {
        if (restaurant_id == null){
            throw new BaseException(BAD_QS_RESTAURANT_ID_RESERVATION_ERROR);
        } else if (day == null) {
            throw new BaseException(BAD_QS_DAY_RESERVATION_ERROR);
        }

        ReservationDayResponse response = reservationService.reserveDay(restaurant_id, day);
        return new BaseResponse<>(response);
    }
}
