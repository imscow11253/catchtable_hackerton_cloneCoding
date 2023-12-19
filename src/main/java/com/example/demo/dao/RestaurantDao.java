package com.example.demo.dao;

import com.example.demo.dto.restaurant.GetRestaurantResponse;
import com.example.demo.dto.restaurant.RestaurantInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RestaurantDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RestaurantDao(DataSource dataSource){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public GetRestaurantResponse getRestaurantlist(String category, int pageId) {
        int has_next;
        int cur_page = pageId;
        int pageSize = 1;
        int offset = (pageId - 1) * pageSize;
        String sql = "select r.restaurant_id, r.restaurant_name, r.restaurant_img, r.description, r.location, r.lunch_price, r.dinner_price, r.maximum_price, r.minimum_price, COALESCE(AVG(rev.rate), 0) as rate " +
                "from restaurant r left join review rev on r.restaurant_id=rev.restaurant_id where r.category=:category "
                + "group by r.restaurant_id, r.restaurant_name, r.restaurant_img, r.description, r.location, r.lunch_price, r.dinner_price, r.maximum_price, r.minimum_price " +
                "LIMIT :pageSize OFFSET :offset";
        Map<String, Object> param = Map.of("category", category,
                "pageSize", pageSize,
                "offset", offset);

        List<RestaurantInformation> ri = jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new RestaurantInformation(
                        rs.getInt("restaurant_id"),
                        rs.getString("restaurant_name"),
                        rs.getString("restaurant_img"),
                        rs.getString("description"),
                        rs.getString("location"),
                        rs.getInt("lunch_price"),
                        rs.getInt("dinner_price"),
                        rs.getInt("maximum_price"),
                        rs.getInt("minimum_price"),
                        rs.getFloat("rate"))
        );
        offset = (pageId) * pageSize;

        String sql2 = "select r.restaurant_id, r.restaurant_name, r.restaurant_img, r.description, r.location, r.lunch_price, r.dinner_price, r.maximum_price, r.minimum_price, COALESCE(AVG(rev.rate), 0) as rate " +
                "from restaurant r left join review rev on r.restaurant_id=rev.restaurant_id where r.category=:category "
                + "group by r.restaurant_id, r.restaurant_name, r.restaurant_img, r.description, r.location, r.lunch_price, r.dinner_price, r.maximum_price, r.minimum_price " +
                "LIMIT :pageSize OFFSET :offset";

        Map<String, Object> param2 = Map.of("category", category,
                "pageSize", pageSize,
                "offset", offset);

        List<RestaurantInformation> ri2 = jdbcTemplate.query(sql2, param2,
                (rs, rowNum) -> new RestaurantInformation(
                        rs.getInt("restaurant_id"),
                        rs.getString("restaurant_name"),
                        rs.getString("restaurant_img"),
                        rs.getString("description"),
                        rs.getString("location"),
                        rs.getInt("lunch_price"),
                        rs.getInt("dinner_price"),
                        rs.getInt("maximum_price"),
                        rs.getInt("minimum_price"),
                        rs.getFloat("rate"))
        );

        has_next = (!ri2.isEmpty()) ? 1 : 0;

        return new GetRestaurantResponse(
                ri,
                has_next,
                cur_page
        );
    }



}
