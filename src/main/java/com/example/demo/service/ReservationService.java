package com.example.demo.service;

import com.example.demo.dao.ReservationDao;
import com.example.demo.dto.Reservation.GetRequestReservationWeek;
import com.example.demo.dto.Reservation.GetResponseReservationWeek;
import com.example.demo.dto.Reservation.GetResponseReservationWeekList;
import com.example.demo.exception.reservation.ReservationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.response.status.BaseExceptionResponseStatus.INVALID_WEEK_VALUE;
import static com.example.demo.response.status.BaseExceptionResponseStatus.NO_EXIST_RESTAURANT_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationDao reservationDao;

    public GetResponseReservationWeekList reservationAvailableDayByWeek(GetRequestReservationWeek getRequestReservationWeek) {
        log.info("ReservationService.reservationAvailableDatByWeek");

        this.checkRestaurantId(getRequestReservationWeek.getReservation_id());
        this.checkWeek(getRequestReservationWeek.getWeek());

        List<GetResponseReservationWeek> list = this.reservationDao.getListOfDay(getRequestReservationWeek);
        GetResponseReservationWeekList getResponseReservationWeekList = new GetResponseReservationWeekList();
        int index =0;
        for(GetResponseReservationWeek a : list) {
            getResponseReservationWeekList.result[index] = a;
            long day_id = this.reservationDao.getDayIdByDay(a.getDay());
            getResponseReservationWeekList.result[index++].setReservation_time(this.reservationDao.getListOfTime(day_id, getRequestReservationWeek.getReservation_people()));
        }

        return getResponseReservationWeekList;
    }

    private void checkWeek(int week) {
        if(week > 4 || week < 1){
            throw new ReservationException(INVALID_WEEK_VALUE);
        }
    }

    private void checkRestaurantId(long restaurant_id){
        if(!this.reservationDao.hasRestaurantId(restaurant_id)){
            throw new ReservationException(NO_EXIST_RESTAURANT_ID);
        }
    }
}
