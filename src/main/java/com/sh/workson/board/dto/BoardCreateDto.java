package com.sh.workson.board.dto;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.board.entity.Type;
import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class BoardCreateDto {

    @NotEmpty(message = "제목은 필수 입력 사항입니다.")
    private String title;
    @Enumerated(EnumType.STRING)
    private Type type;
    @NotEmpty(message = "내용은 필수 입력 사항입니다.")
    private String content;
    private List<AttachmentCreateDto> attachments = new ArrayList<>();

    private Employee employee;
    public void addAttachmentCreateDto(AttachmentCreateDto attachmentCreateDto){
        this.attachments.add(attachmentCreateDto);
    }

}
