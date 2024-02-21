package com.sh.workson.schedule.repository;

import com.sh.workson.employee.entity.Employee;
import com.sh.workson.schedule.entity.ScheduleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleCategoryRepository extends JpaRepository<ScheduleCategory, Long> {
    // @Query("from ScheduleCategory sc left join fetch sc.emp_id e where sc.emp_id = :employee")
    // List<ScheduleCategory> findByEmpId(@Param("employee") Employee employee);
    @Query("from ScheduleCategory sc left join fetch sc.employee e where sc.employee.id = :empId")
    List<ScheduleCategory> findByEmpId(@Param("empId") Long empId);

    @Query("from ScheduleCategory sc left join fetch sc.employee e where sc.employee = :employee and sc.name = :name")
    ScheduleCategory findByNameAndEmpId(Employee employee, String name);


    @Query("from ScheduleCategory sc left join fetch sc.employee e where sc.id = :id")
    Employee findEmployeeById(@Param("id") Long id);
}

//sc.employee.id = :empId and