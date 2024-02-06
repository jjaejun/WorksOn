package com.sh.workson.chat.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ChatRoomCreateDto {
    private String name;
    private List<Long> empId = new ArrayList<>();
}