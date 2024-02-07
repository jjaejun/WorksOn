package com.sh.workson.attachment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "attachment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {
    @Id
    @GeneratedValue(generator = "seq_attachment_id_generator")
    @SequenceGenerator(
            name = "seq_attachment_id_generator",
            sequenceName = "seq_attachment_id",
            initialValue = 1,
            allocationSize = 50
    )
    private Long id;
    @Column(name = "board_id")
    private Long boardId; // 객체자체를 참조할 필요가 없음
    private String originalFilename;
    private String key;
    private String url;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
