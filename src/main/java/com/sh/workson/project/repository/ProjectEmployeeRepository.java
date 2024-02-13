package com.sh.workson.project.repository;

import com.sh.workson.project.entity.Project;
import com.sh.workson.project.entity.ProjectEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectEmployeeRepository extends JpaRepository<ProjectEmployee, Long> {

    @Query("from ProjectEmployee pe join fetch pe.employee e join fetch e.department join fetch e.position where pe.projectId = :projectId")
    List<ProjectEmployee> findAllProjectEmployeesByProjectID(Long projectId);
}
