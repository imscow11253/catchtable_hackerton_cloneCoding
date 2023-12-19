package com.example.demo.dto.restaurant;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRestaurantResponse {
    List<RestaurantInformation> restaurant_list = new ArrayList<>();
    int has_next;
    int cur_page;
}
