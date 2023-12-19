package com.example.demo.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReservationDayResponse {
    List<Integer> possible_time;
}
