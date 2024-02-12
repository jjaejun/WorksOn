package com.sh.workson.schedule.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name="Schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class Schedule {
    @Id
    @GeneratedValue(generator = "seq_schedule_id_generator")
    @SequenceGenerator(
            name="seq_schedule_id_generator",
            sequenceName = "seq_schedule_id")
    private Long id;
    @Column(nullable = false, name = "title")
    private String title;
    private String content;
    @Column(nullable = false, name = "start_time")
    @Timestamp
    private LocalDateTime startTime;
    @Column(nullable = false, name = "end_time")
    @Timestamp
    private LocalDateTime endTime;
    @OneToOne
    private ScheduleCategory scheduleCategory;
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee emp_id;

}
