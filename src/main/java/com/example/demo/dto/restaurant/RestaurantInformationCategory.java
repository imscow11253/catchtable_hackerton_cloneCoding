package com.example.demo.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantInformationCategory {
    private int restaurant_id;
    private String restaurant_name;
    private String restaurant_img;
    private String description;
    private String location;
    private int lunch_price;
    private int dinner_price;
    private int maximum_price;
    private int minimum_price;
    private float rate = 0;
}
