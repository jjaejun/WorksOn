package com.sh.workson.resource.entity;

import com.sh.workson.reservation.entity.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_resource")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_tb_resource_id_generator")
    @SequenceGenerator(name = "seq_tb_resource_id_generator", sequenceName = "seq_tb_resource_id", initialValue = 1, allocationSize = 50)
    private Long id;
    private String name;
    private String location;
    private String info;
    @Enumerated(EnumType.STRING)
    private Type type;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_resource_id")
    @Builder.Default
    private List<Reservation> reservations = new ArrayList<>();
}
