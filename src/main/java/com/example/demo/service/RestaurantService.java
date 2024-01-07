package com.example.demo.service;

import com.example.demo.dao.RestaurantDao;
import com.example.demo.dto.restaurant.GetRestaurantDetail;
import com.example.demo.dto.restaurant.GetRestaurantName;
import com.example.demo.dto.restaurant.GetRestaurantResponseCategory;
import com.example.demo.dto.restaurant.GetRestaurantResponseLocation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantDao restaurantDao;
    public GetRestaurantResponseCategory getRestaurantListCategory(String category, int pageId) {
        log.info("[RestaurantService.getRestaurantList]");
        return restaurantDao.getRestaurantlistCategory(category, pageId);
    }

    public GetRestaurantResponseLocation getRestaurantListLocation(String location, int pageId){
        log.info("[RestaurantService.getRestaurantListLocation]");
        return restaurantDao.getRestaurantlistLocation(location, pageId);
    }

    public GetRestaurantDetail getRestaurantDetail(int restaurant_id){
        log.info("[RestaurantService.getRestaurantListDetail]");
        return restaurantDao.getRestaurantDetail(restaurant_id);
    }

    public List<GetRestaurantName> getRestaurantName(String restaurant_name){
        return restaurantDao.getRestaurantName(restaurant_name);
    }
}

