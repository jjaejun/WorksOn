package com.sh.workson.chat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatLogReturnDto {
    private String content;
    private Long empId;
    private String name;
}
