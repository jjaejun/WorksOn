package com.sh.workson.board.entity;

import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_board_id_generator")
    @SequenceGenerator(
            name = "seq_board_id_generator",
            sequenceName = "seq_board_id",
            initialValue = 1,
            allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;
    private String content;
    @Column(columnDefinition = "number" ) // 컬럼의 데이터 타입을 number로 지정
    private int viewCount;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime updatedAt;
    @OneToMany
    @JoinColumn(name = "board_id") // attachment.board_id 컬럼 참조
    @Builder.Default
    private List<Attachment> attachments = new ArrayList<>();

}
