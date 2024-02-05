package com.sh.workson.authority.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "authority",
        uniqueConstraints =
                @UniqueConstraint (
                name = "uq_authority_emp_id_name",
                columnNames = {"emp_id", "name"}
                )
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_authority_id_generator")
    @SequenceGenerator(
            name = "seq_authority_id_generator",
            sequenceName = "seq_authority_id"
    )
    private Long id;
    @Column(name = "emp_id")
    private Long empId;
    @Column
    @Enumerated(EnumType.STRING)
    private RoleAuth name;

}
