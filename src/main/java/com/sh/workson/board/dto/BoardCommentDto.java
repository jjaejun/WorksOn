package com.sh.workson.board.dto;

import com.sh.workson.board.entity.Board;
import com.sh.workson.board.entity.BoardComment;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardCommentDto {
    private String id;
    private String content;
    private BoardComment parentComment;
    private int commentLevel = 1;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long employeeId; // employeeId 추가
}




