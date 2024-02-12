package com.sh.workson.attachment.dto;

import com.sh.workson.attachment.entity.AttachType;
import com.sh.workson.board.entity.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentCreateDto {
    // 프로젝트, 게시판, 일정등 참조하는 게시글의 id
    private Long boardId;
    @Enumerated(EnumType.STRING)
    private AttachType type;
    private String originalFilename; // 업로드한 파일명
    private String key; // S3파일 식별자
    private String url; // S3파일 url
}
