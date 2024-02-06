package com.sh.workson.project.repository;

import com.sh.workson.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("from Project p join p.projectEmployees")
    List<Project> findAll();

}
