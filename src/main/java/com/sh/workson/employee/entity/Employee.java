package com.sh.workson.employee.entity;

import com.sh.workson.authority.entity.Authority;
import com.sh.workson.department.entity.Department;
import com.sh.workson.position.entity.Position;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_employee_id_generator")
    @SequenceGenerator(
            name = "seq_employee_id_generator",
            sequenceName = "seq_employee_id"
    )
    private Long id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private int rest;
    private String email;
    private String phone;
    private LocalDate birthday;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;
    private String profileOriginalName;
    private String profileKey;
    private String profileUrl;
    private int seed;
    private int cherry;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private List<Authority> authorities;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", unique = false, nullable = true)
    private Position position;

    // 부서와 사원은 양방향 OneToMany ManyToOne으로 변경될 예정입니다..
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_id", unique = false, nullable = true)
    private Department department;

    /**
     * 민정
     */






    /**
     * 재준
     */








    /**
     * 우진
     */







    /**
     * 민준
     */








    /**
     * 준희
     */









    /**
     * 무진
     */










}
