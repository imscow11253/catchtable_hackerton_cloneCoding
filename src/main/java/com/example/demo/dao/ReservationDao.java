package com.example.demo.dao;

import com.example.demo.dto.reservation.GetRequestReservationWeek;
import com.example.demo.dto.reservation.GetResponseReservationWeek;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
@Slf4j
public class ReservationDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Integer> getAvailableTimeforDay(Integer restaurant_id, Date date) {
        String sql = "SELECT t.time " +
                "FROM time t " +
                "INNER JOIN reservation_day rd ON t.day_id = rd.day_id " +

                "WHERE rd.restaurant_id=? " +
                "AND rd.day=? " +
                "AND t.is_reservation='YES'";
        List<Timestamp> timestampList = jdbcTemplate.queryForList(sql, Timestamp.class,restaurant_id, date);


        // Stream을 사용하여 List<Timestamp>를 List<Integer>으로 변환
        List<Integer> hours = timestampList.stream()
                .map(timestamp -> {
                    int hour = timestamp.toLocalDateTime().getHour(); // Timestamp를 LocalDateTime으로 변환하여 시간을 가져옴 (0~23)
                    return hour;
                }).toList();

        return hours;
    }

    public boolean hasRestaurantId(long restaurantId) {
        log.info("ReservationDao.hasRestaurantId");

        String sql = "SELECT EXISTS(SELECT restaurant_id FROM `restaurant` WHERE restaurant_id=?)";
        return Boolean.TRUE.equals(this.jdbcTemplate.queryForObject(sql, boolean.class, restaurantId));
    }

    public List<GetResponseReservationWeek> getListOfDay(GetRequestReservationWeek getRequestReservationWeek) {
        log.info("Reservation.getListOfAvailableDay");

        String sql = "SELECT `day_id`, `day` FROM reservation_day ORDER BY `day` ASC LIMIT ?,7";
        int startDay = (getRequestReservationWeek.getWeek() -1 ) * 7;
        return this.jdbcTemplate.query(sql,
                new RowMapper<GetResponseReservationWeek>() {
                    public GetResponseReservationWeek mapRow(ResultSet rs, int rowNum) throws SQLException {
                        GetResponseReservationWeek getResponseReservationWeek = new GetResponseReservationWeek();
                        getResponseReservationWeek.setDay(rs.getString("day"));
                        return getResponseReservationWeek;
                    }
                }, startDay
        );


    }

    public long getDayIdByDay(String day) {
        String sql = "SELECT day_id FROM Reservation_day WHERE day=?";
        return this.jdbcTemplate.queryForObject(sql, long.class, day);
    }

    public int[] getListOfTime(long day_id, int reservation_people) {
        log.info("Reservation.getListOfAvailableDay");

        String sql = "SELECT time FROM time WHERE day_id=? AND people_num>=? AND is_reservation='YES'";
        List<Timestamp> timestampList = this.jdbcTemplate.queryForList(sql, Timestamp.class, day_id, reservation_people);
        /*for(Timestamp a : timestampList){
            log.info("logis" + a.toString());
        }*/
        // Stream을 사용하여 List<Timestamp>를 List<Integer>으로 변환
        List<Integer> hours = timestampList.stream()
                .map(timestamp -> {
                    int hour = timestamp.toLocalDateTime().getHour(); // Timestamp를 LocalDateTime으로 변환하여 시간을 가져옴 (0~23)
                    return hour;
                }).toList();

        int[] reservation_time = new int[hours.size()];
        for(int i =0;i<hours.size(); i++){
            reservation_time[i] = hours.get(i);
        }

        return reservation_time;

    }

    public Integer saveReservation(long userId, long restaurantId, Date visit_date, Time visit_time, long number_of_people, String consumer_memo){
        log.info("ReservationDao.saveReservation");
        String sql = "INSERT INTO reservation (member_id, restaurant_id, visit_date, visit_time, number_of_people, cosumer_memo) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println(restaurantId);
        jdbcTemplate.update(sql, userId, restaurantId, visit_date, visit_time, number_of_people, consumer_memo);

        String select_sql = "SELECT reservation_id FROM reservation WHERE member_id=? AND restaurant_id=? AND visit_date=?";
        return jdbcTemplate.queryForObject(select_sql, Integer.class, userId, restaurantId, visit_date);
    }
}
