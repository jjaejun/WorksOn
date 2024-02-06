package com.sh.workson.chat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat_read")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRead {
    @Id
    @Column(name = "chat_log_id")
    private Long chatLogId;
    @Column(name = "employee_id")
    private Long empId;
}

