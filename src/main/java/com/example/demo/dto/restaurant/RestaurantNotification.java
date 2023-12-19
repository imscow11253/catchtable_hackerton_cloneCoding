package com.example.demo.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantNotification {
    private String title;
    private String detail;
    private String start_date;
    private String end_date;
}
