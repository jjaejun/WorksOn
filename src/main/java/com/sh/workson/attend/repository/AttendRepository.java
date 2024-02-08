package com.sh.workson.attend.repository;

import com.sh.workson.attend.entity.Attend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AttendRepository extends JpaRepository<Attend, Long> {
        @Query("from Attend a join fetch a.employee where a.id = :id order by a.startAt desc")
        Attend findByAttendId(Long id);

        @Query("from Attend a join fetch a.employee where a.employee.id = :id order by a.startAt desc")
        Page<Attend> findAll(Pageable pageable,Long id);

        @Query(value = """
                select 
                    *
                from 
                    attend
                where
                    employee_id = :id
                ORDER BY 
                    start_at DESC
                FETCH FIRST 1 ROW ONLY
        """, nativeQuery = true)
        Attend findByOrderByStartAt(Long id);

}
