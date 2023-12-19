package com.example.demo.controller;

import com.example.demo.dto.base.BaseResponse;
import com.example.demo.dto.reservation.*;
import com.example.demo.exception.BaseException;
import com.example.demo.service.ReservationService;
import com.example.demo.util.JwtProvider;
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
    private final JwtProvider jwtProvider;

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

    @GetMapping("/week")
    public BaseResponse<GetResponseReservationWeekList> reservationAvailableDayByWeek(@RequestParam long restaurant_id, @RequestParam int week, @RequestParam int reservation_people){
        log.info("ReservationController.reservationAvailableDayByWeek");

        GetRequestReservationWeek getRequestReservationWeek = new GetRequestReservationWeek(restaurant_id, week, reservation_people);

        return new BaseResponse<>(this.reservationService.reservationAvailableDayByWeek(getRequestReservationWeek));
    }

    @PostMapping
    public BaseResponse<ReservationCreateResponse> createReservation(
            @RequestHeader("Authorization") String token,
            @RequestBody PostReservationCreateRequest requestBody,
            BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()){
            if (bindingResult.hasErrors()){
                throw new BaseException(BAD_FIELD_RESERVATION_CREATE_ERROR);
            }
        }
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ", ""));
        return new BaseResponse<>(new ReservationCreateResponse(1));
    }
}
