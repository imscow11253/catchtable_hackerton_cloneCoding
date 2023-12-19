package com.example.demo.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRestaurantDetail {

    private String restaurant_name;
    private String location;
    private String description;
    private String restaurant_img;
    private float rate;
    List<RestaurantNotification> notificationList;
    List<RestaurantOpenday> opendayList;
}
