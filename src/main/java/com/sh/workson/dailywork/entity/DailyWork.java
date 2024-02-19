package com.sh.workson.dailywork.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "daily_work")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyWork {
    @Id
    @GeneratedValue(generator = "seq_daily_work_id")
    @SequenceGenerator(
            name = "seq_daily_work_id_generator",
            sequenceName = "seq_daily_work_id"
    )
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private int cherryCount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
