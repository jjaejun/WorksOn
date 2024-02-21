package com.sh.workson.reservation.dto;

import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.reservation.entity.Reservation;
import com.sh.workson.resource.entity.Type;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ReservationAttachmentDto {
    private Long id;
    private String name;
    private String location;
    private String info;
    private Type type;
    @Builder.Default
    private List<Reservation> reservations = new ArrayList<>();
    @Builder.Default
    private List<Attachment> attachments = new ArrayList<>();
}
