package com.sh.workson.employee.dto;

import com.sh.workson.authority.entity.Authority;
import com.sh.workson.department.entity.Department;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.entity.WorkStatus;
import com.sh.workson.position.entity.Position;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EmployeeCreateDto {

    private String password;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthday;
    private LocalDateTime createdAt;
    private Long position;
    private Long dept;

    public Employee toEmployee() {
        return Employee.builder()
                .email(email)
                .name(name)
                .password(password)
                .birthday(birthday)
                .createdAt(createdAt)
                .phone(phone)
                .department(Department.builder()
                        .id(dept)
                        .build())
                .position(Position.builder()
                        .id(position)
                        .build())
                .build();
    }
}
