package com.sh.workson.reservation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationListDto {
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String content;
    private Integer count;
    private String empName;
    private Long resourceId;
}
