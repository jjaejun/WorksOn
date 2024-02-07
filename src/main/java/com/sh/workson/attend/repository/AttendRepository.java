package com.sh.workson.attend.repository;

import com.sh.workson.attend.entity.Attend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AttendRepository extends JpaRepository<Attend, Long> {
        @Query("from Attend a join fetch a.employee where a.id = :id")
        Attend findByAttendId(Long id);

        @Query("from Attend a join fetch a.employee where a.employee.id = :id")
        Page<Attend> findAll(Pageable pageable,Long id);
}
