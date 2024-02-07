package com.sh.workson.attend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attend_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendRequest {
    @Id
    @GeneratedValue(generator = "seq_attend_request_id_generator")
    @SequenceGenerator(
            name = "seq_attend_request_id_generator",
            sequenceName = "seq_attend_request_id"
    )
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String content;
    @Enumerated(EnumType.STRING)
    private CheckAr checkAr;

}
