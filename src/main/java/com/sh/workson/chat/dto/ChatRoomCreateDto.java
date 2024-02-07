package com.sh.workson.chat.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class ChatRoomCreateDto {
    private String name;
    @Builder.Default
    private Set<Long> empId = new LinkedHashSet<>();
}