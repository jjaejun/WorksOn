package com.sh.workson.schedule.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "schedule_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class ScheduleCategory {
    @Id
    @GeneratedValue(generator = "seq_schedule_category_id_generator")
    @SequenceGenerator(
            name="seq_schedule_category_id_generator",
            sequenceName = "seq_schedule_category_id")
    private Long id;
    private String name;
    private String color;
    @ManyToOne
    // @JoinColumn(name="id", insertable = false, updatable = false)
    @JoinColumn(name="emp_id")
    private Employee employee;
}
