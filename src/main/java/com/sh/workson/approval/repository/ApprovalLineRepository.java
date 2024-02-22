package com.sh.workson.approval.repository;

import com.sh.workson.approval.entity.ApprovalLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalLineRepository extends JpaRepository<ApprovalLine, Long> {
}
