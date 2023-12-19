package com.example.demo.dto.reservation;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostReservationCreateRequest {

    @NotBlank(message = "restaurant_id가 존재하지 않습니다.")
	private Integer restaurant_id;

    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "날짜는 YYYY-MM-DD 형식의 문자열이어야 합니다.")
    private String visit_date;

    @NotBlank
    @Pattern(regexp = "^\\d{2}:\\d{2}:\\d{2}$", message = "시간은 HH:mm:ss 형식의 문자열이어야 합니다.")
    private String visit_time;

    @NotBlank
    private Integer number_of_people;

    @NotNull
    private String cosumer_memo;

}
