package com.sh.workson.attachment.repository;

import com.sh.workson.attachment.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

}
