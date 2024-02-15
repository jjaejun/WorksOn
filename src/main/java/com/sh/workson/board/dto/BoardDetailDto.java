package com.sh.workson.board.dto;

import com.sh.workson.attachment.dto.AttachmentDetailDto;
import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.board.entity.Type;
import com.sh.workson.employee.dto.EmployeeDetailDto;
import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailDto {
    private Long id;
    private Type type;
    private String title;
    private Employee employee ;
    private String content;
    private int viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Attachment> attachments = new ArrayList<>();
//    private List<CommentDetailDto> comments = new ArrayList<>(); // comments 속성 추가
//    private List<CommentDto> commentList;



}
