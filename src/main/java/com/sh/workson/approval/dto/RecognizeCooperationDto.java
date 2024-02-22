package com.sh.workson.approval.dto;

import lombok.Data;

@Data
public class RecognizeCooperationDto {
    private Long id; // approval_line# id
    private String status; // approval_line# status
    private String sign; // approval_line# sign
    private String isLast; // html에서 마지막요소 검사여부
    private Long lineId; // lineId
}
