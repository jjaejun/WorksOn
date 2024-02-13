package com.sh.workson.board.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "board_comment")
@Data
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_board_comment_id_generator")
    @SequenceGenerator(
            name = "seq_board_comment_id_generator",
            sequenceName = "seq_board_comment_id",
            initialValue = 1,
            allocationSize = 50)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false, length = 4000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private BoardComment parentComment;

    @Column(nullable = false)
    private int commentLevel = 1;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;
}