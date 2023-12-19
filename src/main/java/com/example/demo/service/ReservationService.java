package com.example.demo.service;

import com.example.demo.dao.ReservationDao;
import com.example.demo.dto.reservation.ReservationDayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import static java.lang.Integer.parseInt;

@RequiredArgsConstructor
@Service
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
}
