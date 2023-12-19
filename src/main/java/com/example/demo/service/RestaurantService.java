package com.example.demo.service;

import com.example.demo.dao.RestaurantDao;
import com.example.demo.dto.restaurant.GetRestaurantResponse;
import com.example.demo.dto.restaurant.RestaurantInformation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantDao restaurantDao;
    public GetRestaurantResponse getRestaurantList(String category, int pageId) {
        log.info("[RestaurantService.getRestaurantList]");
        return restaurantDao.getRestaurantlist(category, pageId);
    }
}
