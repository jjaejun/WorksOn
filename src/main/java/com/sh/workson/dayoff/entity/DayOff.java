package com.sh.workson.dayoff.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "dayoff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DayOff {
    @Id
    @GeneratedValue(generator = "seq_dayoff_id")
    @SequenceGenerator(
            name = "seq_dayoff_id_generator",
            sequenceName = "seq_dayoff_id"
    )
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Long count;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private String content;

}
