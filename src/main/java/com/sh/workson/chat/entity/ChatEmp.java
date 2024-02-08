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
@Table(name = "chat_emp")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatEmp {
    @Id
    @Column(name = "chat_room_id")
    private Long chatRoomId;
    @Column(name = "employee_id")
    private Long employeeId;
}
