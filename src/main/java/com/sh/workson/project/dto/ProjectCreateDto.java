package com.sh.workson.project.dto;

import ch.qos.logback.core.util.Loader;
import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.project.entity.Status;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectCreateDto {
    private String title;
    private LocalDate startAt;
    private LocalDate endAt;
    private String status;

    // 사용자 정보
    private Employee Employee;

    // 사원 목록
    List<Long> createEmp = new ArrayList<>();
    List<Long> readEmp = new ArrayList<>();

    // 첨부파일 목록
    List<AttachmentCreateDto> attaches = new ArrayList<>();

    public void setStartAt(String startAt) {
        this.startAt = LocalDate.parse(startAt);
    }
    public void setEndAt(String endAt) {
        this.endAt = LocalDate.parse(endAt);
    }

    public void setStatus(String status){
        if(startAt.isBefore(LocalDate.now()) || startAt.equals(LocalDate.now())){
            this.status = "ING";
        }
        else if(startAt.isAfter(LocalDate.now())){
            this.status = "NOT_YET";
        }
    }

    public void addAttachmentCreateDto(AttachmentCreateDto attachmentCreateDto) {
        this.attaches.add(attachmentCreateDto);
    }
}
