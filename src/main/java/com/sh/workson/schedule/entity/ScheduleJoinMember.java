package com.sh.workson.schedule.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name ="schedule_join_member")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleJoinMember {
    @Id
    @GeneratedValue(generator = "seq_schedule_join_member_id_generator")
    @SequenceGenerator(
            name="seq_schedule_join_member_id_generator",
            sequenceName = "seq_schedule_join_member_id")
    private Long id;
    @OneToOne
    @JoinColumn(name="schedule_id")
    private Schedule scheduleId;

    @OneToMany
    @JoinColumn(name = "id") // employee 의 id만 참조
    @Builder.Default
    private List<Employee> employees = new ArrayList<>();
}
