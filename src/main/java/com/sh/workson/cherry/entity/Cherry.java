package com.sh.workson.cherry.entity;

import com.sh.workson.dailywork.entity.DailyWork;
import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cherry")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cherry {
    @Id
    @GeneratedValue(generator = "seq_cherry_id")
    @SequenceGenerator(
            name = "seq_cherry_id_generator",
            sequenceName = "seq_cherry_id"
    )
    private Long id;
    private int getCherry;
    private String cherryContent;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "daily_work_id")
    private DailyWork dailyWork;
}
