package com.sh.workson.dayoff.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DayOffListDto {
    private Long id;
    private Type type;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Long count;
    private String content;

}
