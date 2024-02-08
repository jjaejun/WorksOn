package com.sh.workson.board.dto;

import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class BoardListDto {
    private Long id;
    private String type;
    private String title;
    private String empId;  //Employee#name
    private String content;
    private Long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int attachCount;

}
