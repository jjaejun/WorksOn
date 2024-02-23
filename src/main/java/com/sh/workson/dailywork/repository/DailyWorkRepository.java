package com.sh.workson.dailywork.repository;

import com.sh.workson.dailywork.entity.DailyWork;
import com.sh.workson.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;

public interface DailyWorkRepository extends JpaRepository<DailyWork, Long> {


    @Query("from DailyWork w join fetch w.employee where w.employee.id = :id order by w.createdAt desc")
    Page<DailyWork> findAll(Pageable pageable, @Param("id")Long id);


}
