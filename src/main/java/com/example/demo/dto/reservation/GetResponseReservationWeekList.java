package com.example.demo.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetResponseReservationWeekList {
    public GetResponseReservationWeek[] result = new GetResponseReservationWeek[7];
}