package com.example.demo.dao;

import com.example.demo.dto.restaurant.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RestaurantDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RestaurantDao(DataSource dataSource){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public GetRestaurantResponseCategory getRestaurantlistCategory(String category, int pageId) {
        int has_next;
        int cur_page = pageId;
        int pageSize = 3;
        int offset = (pageId - 1) * pageSize;
        String sql = "select r.restaurant_id, r.restaurant_name, r.restaurant_img, r.description, r.location, r.lunch_price, r.dinner_price, r.maximum_price, r.minimum_price, COALESCE(ROUND(AVG(rev.rate),1), 0) as rate " +
                "from restaurant r left join review rev on r.restaurant_id=rev.restaurant_id where r.category=:category "
                + "group by r.restaurant_id, r.restaurant_name, r.restaurant_img, r.description, r.location, r.lunch_price, r.dinner_price, r.maximum_price, r.minimum_price " +
                "LIMIT :pageSize OFFSET :offset";
        Map<String, Object> param = Map.of("category", category,
                "pageSize", pageSize,
                "offset", offset);

        List<RestaurantInformationCategory> ri = jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new RestaurantInformationCategory(
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

        String sql2 = "select r.restaurant_id, r.restaurant_name, r.restaurant_img, r.description, r.location, r.lunch_price, r.dinner_price, r.maximum_price, r.minimum_price, COALESCE(ROUND(AVG(rev.rate),1), 0) as rate " +
                "from restaurant r left join review rev on r.restaurant_id=rev.restaurant_id where r.category=:category "
                + "group by r.restaurant_id, r.restaurant_name, r.restaurant_img, r.description, r.location, r.lunch_price, r.dinner_price, r.maximum_price, r.minimum_price " +
                "LIMIT :pageSize OFFSET :offset";

        Map<String, Object> param2 = Map.of("category", category,
                "pageSize", pageSize,
                "offset", offset);

        List<RestaurantInformationCategory> ri2 = jdbcTemplate.query(sql2, param2,
                (rs, rowNum) -> new RestaurantInformationCategory(
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

        return new GetRestaurantResponseCategory(
                ri,
                has_next,
                cur_page
        );
    }

    public GetRestaurantResponseLocation getRestaurantlistLocation(String location, int pageId){
        int has_next;
        int cur_page = pageId;
        int pageSize = 3;
        int offset = (pageId - 1) * pageSize;
        String sql = "select r.restaurant_id, r.restaurant_name, r.location, r.description, r.restaurant_img, COALESCE(ROUND(AVG(rev.rate),1), 0) as rate " +
                "from restaurant r left join review rev on r.restaurant_id=rev.restaurant_id where r.location like :location "
                + "group by r.restaurant_id, r.restaurant_name, r.location, r.description, r.restaurant_img " +
                "LIMIT :pageSize OFFSET :offset";
        Map<String, Object> param = Map.of("location", "%"+location+"%",
                "pageSize", pageSize,
                "offset", offset);

        List<RestaurantinformationLocation> ri = jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new RestaurantinformationLocation(
                        rs.getInt("restaurant_id"),
                        rs.getString("restaurant_name"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("restaurant_img"),
                        rs.getFloat("rate"))
        );
        offset = (pageId) * pageSize;

        String sql2 = "select r.restaurant_id, r.restaurant_name, r.location, r.description, r.restaurant_img, COALESCE(ROUND(AVG(rev.rate),1), 0) as rate " +
                "from restaurant r left join review rev on r.restaurant_id=rev.restaurant_id where r.location like :location "
                + "group by r.restaurant_id, r.restaurant_name, r.location, r.description, r.restaurant_img " +
                "LIMIT :pageSize OFFSET :offset";

        Map<String, Object> param2 = Map.of("location", "%"+location+"%",
                "pageSize", pageSize,
                "offset", offset);


        List<RestaurantinformationLocation> ri2 = jdbcTemplate.query(sql2, param2,
                (rs, rowNum) -> new RestaurantinformationLocation(
                        rs.getInt("restaurant_id"),
                        rs.getString("restaurant_name"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("restaurant_img"),
                        rs.getFloat("rate"))
        );

        has_next = (!ri2.isEmpty()) ? 1 : 0;

        return new GetRestaurantResponseLocation(
                ri,
                has_next,
                cur_page
        );
    }


    public GetRestaurantDetail getRestaurantDetail(int restaurant_id){
        String sql = "select r.restaurant_name, r.location, r.description, r.restaurant_img, COALESCE(ROUND(AVG(rev.rate),1), 0) as rate "+
                "from restaurant r left join review rev on r.restaurant_id=rev.restaurant_id where r.restaurant_id=:restaurant_id";

        Map<String, Object> param = Map.of("restaurant_id", restaurant_id);

        RestaurantDetailInfo rdi = jdbcTemplate.queryForObject(sql, param, (rs, rowNum) ->
                new RestaurantDetailInfo(
                        rs.getString("restaurant_name"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("restaurant_img"),
                        rs.getFloat("rate"))
        );

        String sql2 = "select noti.title, noti.detail, noti.start_date, noti.end_date " +
                "from restaurant_notification noti where noti.restaurant_id=:restaurant_id";

        Map<String, Object> param2 = Map.of("restaurant_id", restaurant_id);

        List<RestaurantNotification> rn = jdbcTemplate.query(sql2, param2,
                (rs, rowNum) -> new RestaurantNotification(
                        rs.getString("title"),
                        rs.getString("detail"),
                        rs.getString("start_date"),
                        rs.getString("end_date"))
        );

        String sql3 = "select od.open_time, od.explanation " +
                "from reservation_open_day od where od.restaurant_id=:restaurant_id";

        Map<String, Object> param3 = Map.of("restaurant_id", restaurant_id);

        List<RestaurantOpenday> ro = jdbcTemplate.query(sql3, param3,
                (rs, rowNum) -> new RestaurantOpenday(
                        rs.getString("open_time"),
                        rs.getString("explanation"))
        );


        return new GetRestaurantDetail(
                rdi.getRestaurant_name(),
                rdi.getLocation(),
                rdi.getDescription(),
                rdi.getRestaurant_img(),
                rdi.getRate(),
                rn,
                ro
        );


    }




}
