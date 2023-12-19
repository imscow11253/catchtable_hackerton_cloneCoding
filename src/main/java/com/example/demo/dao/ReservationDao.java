package com.example.demo.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class ReservationDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Integer> getAvailableTimeforDay(Integer restaurant_id, Date date) {
        String sql = "SELECT t.time " +
                "FROM time t" +
                "INNER JOIN reservation_day rd ON t.day_id = rd.day_id " +
                "WHERE rd.restaurant_id=:restaurant_id " +
                "  AND rd.day=:date " +
                "  AND t.is_reservation='NO';";
        List<Timestamp> timestampList = jdbcTemplate.queryForList(sql, Timestamp.class);
        // Stream을 사용하여 List<Timestamp>를 List<Integer>으로 변환
        List<Integer> hours = timestampList.stream()
                .map(timestamp -> {
                    int hour = timestamp.toLocalDateTime().getHour(); // Timestamp를 LocalDateTime으로 변환하여 시간을 가져옴 (0~23)
                    return hour;
                }).toList();

        return hours;
    }
}
