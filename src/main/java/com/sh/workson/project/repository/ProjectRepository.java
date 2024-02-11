package com.sh.workson.project.repository;

import com.sh.workson.employee.entity.Employee;
import com.sh.workson.project.dto.ProjectListDto;
import com.sh.workson.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findAll(Pageable pageable);

    @Query("from Project p join fetch p.projectEmployees where p.id = :id")
    List<Project> findByProjectId(long id);

    @Query("from Project p join fetch p.projectEmployees pe join fetch p.employee where pe.empId = :id and p.status = 'ING'")
    Page<Project> findByEmpId(Long id, Pageable pageable);

    @Query("from Project p join fetch p.projectEmployees pe where p.employee.id = :id and p.status = 'ING'")
    Page<Project> findByOwnerId(Long id, Pageable pageable);
}
