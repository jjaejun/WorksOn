package com.sh.workson.attend.repository;

import com.sh.workson.attend.entity.AttendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendRequestRepository extends JpaRepository<AttendRequest, Long> {
}
