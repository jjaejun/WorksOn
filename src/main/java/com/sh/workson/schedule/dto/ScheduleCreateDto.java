package com.sh.workson.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 스케쥴 생성에 들어갈 요소들
 * 제목
 * 시작일
 * 종료일
 * 사원 아이디
 *
 * 내용
 * 참여 사원 목록
 */
@Data
public class ScheduleCreateDto {
    @NotBlank(message = "일정 제목은 필수입니다.")
    private String title;
    @NotBlank(message = "일정 시작시간은 필수입니다.")
    private LocalDateTime startTime;
    @NotBlank(message = "일정 종료시간은 필수입니다.")
    private LocalDateTime endTime;
    @NotBlank(message = "일정 수행 사원은 필수입니다.")
    private Long empId;

    private String content;
    private List<Long> joinMembers = new ArrayList<>();
}
