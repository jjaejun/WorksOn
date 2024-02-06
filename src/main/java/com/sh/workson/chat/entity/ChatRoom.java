package com.sh.workson.chat.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "chatroom")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_chatroom_id_generator")
    @SequenceGenerator(name = "seq_chatroom_id_generator", sequenceName = "seq_chatroom_id", initialValue = 1, allocationSize = 50)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "chatemp",
            joinColumns = @JoinColumn(name = "chatroom_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    @Builder.Default
    private Set<Employee> chatEmps = new LinkedHashSet<>();

    public void addChatEmps(Employee employee) {
        this.chatEmps.add(employee);
    }
}

