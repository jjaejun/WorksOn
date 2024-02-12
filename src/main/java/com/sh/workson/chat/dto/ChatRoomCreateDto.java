package com.sh.workson.chat.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ChatRoomCreateDto {
    private String name;
    @Builder.Default
    private List<Long> empIds = new ArrayList<>();
}