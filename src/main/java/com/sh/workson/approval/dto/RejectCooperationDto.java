package com.sh.workson.approval.dto;

import lombok.Data;

@Data
public class RejectCooperationDto {
    private Long id; // approval# id
    private Long lineId; // approval_line# id
    private String status; // approval_line# status

}
