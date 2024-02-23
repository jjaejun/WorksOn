package com.sh.workson.board.dto;

import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.board.entity.Type;
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
    @Enumerated(EnumType.STRING)
    private Type type;
    private String title;
    private Long empId;
    private String empName;
    private String content;
    private Long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int attachCount;
}
