package com.sh.workson.chat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_chat_log_id_generator")
    @SequenceGenerator(name = "seq_chat_log_id_generator", sequenceName = "seq_chat_log_id", initialValue = 1, allocationSize = 50)
    private Long id;
    private String content;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "employee_id")
    private Long empId;
    @Column(name = "chat_room_id")
    private Long chatRoomId;
}

