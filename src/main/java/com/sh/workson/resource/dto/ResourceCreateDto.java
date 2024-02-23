package com.sh.workson.resource.dto;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.resource.entity.Type;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResourceCreateDto {
    private String name;
    private String location;
    private String info;
    private Type type;

    // 첨부파일 목록
    List<AttachmentCreateDto> attaches = new ArrayList<>();

    public void addAttachmentCreateDto(AttachmentCreateDto attachmentCreateDto) {
        this.attaches.add(attachmentCreateDto);
    }
}
