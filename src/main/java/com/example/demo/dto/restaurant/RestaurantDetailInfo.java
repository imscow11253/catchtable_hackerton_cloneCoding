package com.example.demo.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDetailInfo {
    private String restaurant_name;
    private String location;
    private String description;
    private String restaurant_img;
    private float rate;
}
