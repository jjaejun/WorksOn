package com.sh.workson.attend.repository;

import com.sh.workson.attend.entity.Attend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface AttendRepository extends JpaRepository<Attend, Long> {
        @Query("from Attend a join fetch a.employee where a.id = :id order by a.startAt desc")
        Attend findByAttendId(@Param("id")Long id);

        @Query("from Attend a join fetch a.employee where a.employee.id = :id order by a.startAt desc")
        Page<Attend> findAll(Pageable pageable,@Param("id")Long id);

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
        Attend findByOrderByStartAt(@Param("id")Long id);

        @Query("FROM Attend a JOIN FETCH a.employee WHERE a.employee.id = :employeeId ORDER BY a.startAt DESC")
        Attend findByStartAtId(@Param("employeeId")Long id);

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
        Attend findAttendByEmployeeId(@Param("id")Long id);

        @Query("from Attend a join fetch a.employee where a.employee.id = :id and a.startAt between :startTime and :endTime order by a.startAt desc")
        Page<Attend> findBetweenSearchDate(Pageable pageable, @Param("id") Long id, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
