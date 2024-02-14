package com.sh.workson.board.dto;

import com.sh.workson.attachment.dto.AttachmentDetailDto;
import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.board.entity.Type;
import com.sh.workson.employee.dto.EmployeeDetailDto;
import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class BoardDetailDto {
    private Long id;
    private Type type;
    private String title;
    private EmployeeDetailDto employee;
    private String content;
    private Long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<AttachmentDetailDto> attachments = new ArrayList<>();
    private List<CommentDetailDto> comments = new ArrayList<>(); // comments 속성 추가
//    private List<CommentDto> commentList;



}
