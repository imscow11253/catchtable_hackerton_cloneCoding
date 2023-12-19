package com.example.demo.service;

import com.example.demo.dao.ReservationDao;
import com.example.demo.dto.reservation.*;
import com.example.demo.exception.reservation.ReservationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static com.example.demo.response.status.BaseExceptionResponseStatus.INVALID_WEEK_VALUE;
import static com.example.demo.response.status.BaseExceptionResponseStatus.NO_EXIST_RESTAURANT_ID;
import static java.lang.Integer.parseInt;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReservationService {

    final ReservationDao reservationDao;
    public ReservationDayResponse reserveDay(String id, String day){
        Integer restaurant_id = parseInt(id);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate;
        try {
            parsedDate = dateFormat.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        Date date = new Date(parsedDate.getTime());

        return new ReservationDayResponse(reservationDao.getAvailableTimeforDay(restaurant_id, date));
    }

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

    public ReservationCreateResponse createReservation(Long userId, PostReservationCreateRequest requestBody){
        Date sqlDate = Date.valueOf(requestBody.getVisit_date());
        Time sqlTime = Time.valueOf(requestBody.getVisit_time());
        Integer reservation_id = reservationDao.saveReservation(userId, requestBody.getRestaurant_id(), sqlDate, sqlTime, requestBody.getNumber_of_people(), requestBody.getCosumer_memo());
        return new ReservationCreateResponse(reservation_id);
    }
}
