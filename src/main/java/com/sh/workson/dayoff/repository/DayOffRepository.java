package com.sh.workson.dayoff.repository;

import com.sh.workson.dayoff.entity.DayOff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DayOffRepository extends JpaRepository<DayOff, Long> {


    @Query("from DayOff d join fetch d.employee where d.employee.id = :id order by d.startAt desc ")
    Page<DayOff> findAll(Pageable pageable, @Param("id") Long id);

    @Query("from DayOff d join fetch d.employee where d.employee.id = :id AND d.startAt >= :startOfYear AND d.startAt <= :endOfYear")
    Page<DayOff> findEqSearchDate(Pageable pageable, @Param("id") Long id, @Param("startOfYear") LocalDateTime startOfYear, @Param("endOfYear") LocalDateTime endOfYear);


}
