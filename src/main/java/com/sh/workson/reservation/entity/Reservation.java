package com.sh.workson.reservation.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_reservation_id_generator")
    @SequenceGenerator(name = "seq_reservation_id_generator", sequenceName = "seq_reservation_id", initialValue = 1, allocationSize = 50)
    private Long id;
    @Column(name = "start_at")
    private LocalDateTime startAt;
    @Column(name = "end_at")
    private LocalDateTime endAt;
    private String content;
    private Integer count;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;
    @Column(name = "tb_resource_id")
    private Long resourceId;
}
