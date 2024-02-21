package com.sh.workson.chat.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChatLogReturnDto {
    private Long empId;
    private String empName;
    private String content;
    private LocalDateTime createdAt;
}
