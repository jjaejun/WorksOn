package com.sh.workson.attend.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attend")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attend {
    @Id
    @GeneratedValue(generator = "seq_attend_id")
    @SequenceGenerator(
            name = "seq_attend_id_generator",
            sequenceName = "seq_attend_id"
    )
    private Long id;
    @CreationTimestamp
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    @Enumerated(EnumType.STRING)
    private State state;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", unique = true)
    private Employee employee;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "attend_id")
    private List<AttendRequest> attendRequests = new ArrayList<>();
}
