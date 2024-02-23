package com.sh.workson.board.dto;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class BoardUpdateDto {
    private Long id;
    private String title;
    private String content;

}
