package com.example.demo.controller;

import com.example.demo.common.response.BaseResponse;
import com.example.demo.dto.restaurant.GetRestaurantResponse;
import com.example.demo.dto.restaurant.RestaurantInformation;
import com.example.demo.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    @GetMapping("/categories")
    public BaseResponse<GetRestaurantResponse> getRestaurantsCategory(
            @RequestParam(name = "category", required = false, defaultValue = "") String category,
            @RequestParam(name = "pageId", required = false, defaultValue = "") int pageId) {
        log.info(category);
        log.info("[RestaurantController.getRestaurants_category]");
//        if (!status.equals("active") && !status.equals("dormant") && !status.equals("deleted")) {
//            throw new UserException(INVALID_USER_STATUS);
//        }
        return new BaseResponse<>(restaurantService.getRestaurantList(category, pageId));
    }
}
