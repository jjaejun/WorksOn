package com.sh.workson.employee.dto;


import com.sh.workson.authority.entity.Authority;
import com.sh.workson.department.entity.Department;
import com.sh.workson.employee.entity.WorkStatus;
import com.sh.workson.position.entity.Position;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.sh.workson.position.entity.Position;

@Data
public class EmployeeDetailDto {
    private Long id;
    private String name;
    private String email;
    private String profileUrl;
    private Position position;
    private Department department;
}
