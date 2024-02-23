package com.sh.workson.schedule.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class UpdateScheduleDto {
    private Long id;
    @NotEmpty(message = "제목은 필수입력사항 입니다.")
    private String title;
    private Long scheduleCategoryId;
    @NotNull(message = "시작시간은 필수입력사항 입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    private Long empId;
    private String content;
}
