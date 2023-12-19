package com.example.demo.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantinformationLocation {
    private int restaurant_id;
    private String restaurant_name;
    private String location;
    private String description;
    private String restaurant_img;
    private float rate;
}
