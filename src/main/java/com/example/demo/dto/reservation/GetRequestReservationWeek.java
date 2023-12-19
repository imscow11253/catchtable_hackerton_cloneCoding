package com.example.demo.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetRequestReservationWeek {
    private long reservation_id;
    private int week;
    private int reservation_people;
}