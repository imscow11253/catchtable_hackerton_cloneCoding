package com.example.demo.controller;

import com.example.demo.dto.Reservation.GetRequestReservationWeek;
import com.example.demo.dto.Reservation.GetResponseReservationWeek;
import com.example.demo.dto.Reservation.GetResponseReservationWeekList;
import com.example.demo.response.BaseResponse;
import com.example.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/week")
    public BaseResponse<GetResponseReservationWeekList> reservationAvailableDayByWeek(@RequestParam long restaurant_id, @RequestParam int week, @RequestParam int reservation_people){
        log.info("ReservationController.reservationAvailableDayByWeek");

        GetRequestReservationWeek getRequestReservationWeek = new GetRequestReservationWeek(restaurant_id, week, reservation_people);

        return new BaseResponse<>(this.reservationService.reservationAvailableDayByWeek(getRequestReservationWeek));
    }

}
