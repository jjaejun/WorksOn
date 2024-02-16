package com.sh.workson.board.dto;

import com.sh.workson.board.entity.Board;
import com.sh.workson.board.entity.BoardComment;
import com.sh.workson.employee.entity.Employee;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardCommentDto {
    private Long id;
    private String content;
    private BoardComment parentComment;
    private int commentLevel = 1;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Employee employee; // employeeId 추가


}




