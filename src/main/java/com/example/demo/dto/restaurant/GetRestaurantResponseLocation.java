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
public class GetRestaurantResponseLocation {
    List<RestaurantinformationLocation> restaurant_list;
    int has_next;
    int cur_page;
}
