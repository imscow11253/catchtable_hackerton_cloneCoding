package com.example.demo.controller;

import com.example.demo.dto.base.BaseResponse;
import com.example.demo.dto.reservation.PostReservationCreateRequest;
import com.example.demo.dto.reservation.ReservationCreateResponse;
import com.example.demo.dto.reservation.ReservationDayResponse;
import com.example.demo.exception.BaseException;
import com.example.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.dto.base.BaseExceptionResponseStatus.*;

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

//    @PostMapping
//    public BaseResponse<ReservationCreateResponse> createReservation(@RequestBody PostReservationCreateRequest, BindingResult bindingResult){
//        if (bindingResult.hasErrors()){
//            if (bindingResult.hasErrors()){
//                throw new BaseException(BAD_FIELD_RESERVATION_CREATE_ERROR);
//            }
//        }
//    }
}
