package com.example.demo.controller;

import com.example.demo.common.response.BaseResponse;
import com.example.demo.dto.restaurant.GetRestaurantDetail;
import com.example.demo.dto.restaurant.GetRestaurantResponseCategory;
import com.example.demo.dto.restaurant.GetRestaurantResponseLocation;
import com.example.demo.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    @GetMapping("/categories")
    public BaseResponse<GetRestaurantResponseCategory> getRestaurantsCategory(
            @RequestParam(name = "category", required = false, defaultValue = "") String category,
            @RequestParam(name = "pageId", required = false, defaultValue = "") int pageId) {
        log.info(category);
        log.info("[RestaurantController.getRestaurants_category]");
//        if (!status.equals("active") && !status.equals("dormant") && !status.equals("deleted")) {
//            throw new UserException(INVALID_USER_STATUS);
//        }
        return new BaseResponse<>(restaurantService.getRestaurantListCategory(category, pageId));
    }

    @GetMapping("/locations")
    public BaseResponse<GetRestaurantResponseLocation> getRestaurantLocation(
            @RequestParam(name = "location", required = false, defaultValue = "") String location,
            @RequestParam(name = "pageId", required = false, defaultValue = "") int pageId){
        log.info("[RestaurantController.getRestaurants_Location]");

        return new BaseResponse<>(restaurantService.getRestaurantListLocation(location, pageId));
    }

    @GetMapping("")
    public BaseResponse<GetRestaurantDetail> getRestaurantDetail(
            @RequestParam(name = "restaurant_id", required = false, defaultValue = "") int restaurant_id
    ){
        log.info("[RestaurantController.getRestaurants_Detail]");

        return new BaseResponse<>(restaurantService.getRestaurantDetail(restaurant_id));
    }
}
