package com.sh.workson.schedule.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateScheduleDto {
    @NotEmpty(message = "제목은 필수입력사항 입니다.")
    private String title;
    @NotNull(message = "카테고리는 필수입력사항 입니다.")
    private Long ScheduleCategoryId;
    private Long empId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String content;

    // private List<Long> employeeIdList = new ArrayList<>();
}
