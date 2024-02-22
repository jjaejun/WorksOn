package com.sh.workson.resource.dto;

import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.reservation.entity.Reservation;
import com.sh.workson.resource.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceAttachmentDto {
    private Long id;
    private String name;
    private String location;
    private String info;
    private Type type;
    @Builder.Default
    private List<Attachment> attachments = new ArrayList<>();
}
