package com.sh.workson.project.repository;

import com.sh.workson.project.entity.Project;
import com.sh.workson.project.entity.ProjectEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectEmployeeRepository extends JpaRepository<ProjectEmployee, Long> {

}
