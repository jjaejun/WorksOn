package com.sh.workson.schedule.repository;

import com.sh.workson.employee.entity.Employee;
import com.sh.workson.schedule.entity.ScheduleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleCategoryRepository extends JpaRepository<ScheduleCategory, Long> {
    @Query("from ScheduleCategory sc left join fetch sc.emp_id e where sc.emp_id = :employee")
    ScheduleCategory findByEmpId(@Param("employee") Employee employee);
}
