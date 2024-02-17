package com.sh.workson.project.repository;

import com.sh.workson.project.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("from Task t join fetch t.employee e join fetch e.department join fetch e.position join fetch t.project p where t.id = :id")
    Optional<Task> findById(Long id);
}
