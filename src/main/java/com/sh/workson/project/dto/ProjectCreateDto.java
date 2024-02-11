package com.sh.workson.project.dto;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.employee.entity.Employee;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectCreateDto {
    private String title;
    private String startAt;
    private String endAt;
    private String status;

    // 사용자 정보
    private Employee Employee;

    // 사원 목록
    List<Long> createEmp = new ArrayList<>();
    List<Long> readEmp = new ArrayList<>();

    // 첨부파일 목록
    List<AttachmentCreateDto> attaches = new ArrayList<>();

    public void addAttachmentCreateDto(AttachmentCreateDto attachmentCreateDto) {
        this.attaches.add(attachmentCreateDto);
    }
}
