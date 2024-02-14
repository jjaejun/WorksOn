package com.sh.workson.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardCommentCreateDto {
    private Long boardId;
    private String comment;
    private Long parentId;
    private int commentLevel;
    private Long empId;
}
