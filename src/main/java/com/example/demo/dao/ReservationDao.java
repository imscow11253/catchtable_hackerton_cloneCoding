package com.example.demo.dao;

import com.example.demo.dto.Reservation.GetRequestReservationWeek;
import com.example.demo.dto.Reservation.GetResponseReservationWeek;
import com.example.demo.dto.Reservation.GetResponseReservationWeekList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Repository
public class ReservationDao {
    private final JdbcTemplate jdbcTemplate;

    public ReservationDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public boolean hasRestaurantId(long restaurantId) {
        log.info("ReservationDao.hasRestaurantId");

        String sql = "SELECT EXISTS(SELECT restaurant_id FROM `restaurant` WHERE restaurant_id=?)";
        return Boolean.TRUE.equals(this.jdbcTemplate.queryForObject(sql, boolean.class, restaurantId));
    }

    public List<GetResponseReservationWeek> getListOfDay(GetRequestReservationWeek getRequestReservationWeek) {
        log.info("Reservation.getListOfAvailableDay");

        String sql = "SELECT day_id FROM `Reservation_day` ORDER BY day LIMIT ?,7";
        int startDay = getRequestReservationWeek.getWeek()/7 + 1;
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

        String sql = "SELECT time FROM `Time` WHERE day_id=? AND people_num <= ?";
        List<Timestamp> timestampList = this.jdbcTemplate.queryForList(sql, Timestamp.class, day_id, reservation_people);

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
}
