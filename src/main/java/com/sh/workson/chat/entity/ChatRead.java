package com.sh.workson.chat.entity;

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
    private Long chatlogId;
    private Long empId;
}

