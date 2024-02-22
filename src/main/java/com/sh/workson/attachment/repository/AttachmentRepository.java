package com.sh.workson.attachment.repository;

import com.sh.workson.attachment.entity.AttachType;
import com.sh.workson.attachment.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    @Query("from Attachment a join fetch a.employee e join fetch e.position join fetch e.department join fetch e.authorities where a.boardId = :id and a.type = 'PROJECT' order by a.createdAt desc")
    List<Attachment> findAllAttachmentByProjectId(@Param("id") Long id);
    @Query("from Attachment a join fetch a.employee e join fetch e.position join fetch e.department join fetch e.authorities where a.boardId = :id and a.type = 'BOARD' order by a.createdAt desc")
    List<Attachment> findByBoardId(@Param("id")Long id);
}
