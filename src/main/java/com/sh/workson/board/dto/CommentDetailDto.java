package com.sh.workson.board.dto;

import com.sh.workson.board.entity.Board;
import com.sh.workson.board.entity.BoardComment;
import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class CommentDetailDto {
    private Long id;
    private Board board;
    private String content;
    private BoardComment parentComment;
    private int commentLevel = 1;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Employee employee;
}
