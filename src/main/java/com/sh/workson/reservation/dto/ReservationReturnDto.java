package com.sh.workson.reservation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationReturnDto {
    private Long id;
    private String empName;
    private Integer count;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String content;
    private Long resourceId;
}
