package com.sh.workson.project.repository;

import com.sh.workson.project.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("from Task t join fetch t.owner o join fetch o.department join fetch o.position join fetch t.employee e join fetch e.department join fetch e.position join fetch t.project p where t.id = :id order by t.id desc ")
    Optional<Task> findById(Long id);

    @Query("from Task t left join fetch t.owner o left join fetch o.department left join fetch o.position left join fetch t.employee e left join fetch e.department left join fetch e.position left join fetch t.project p where t.owner.id = :id or t.employee.id = :id order by t.id desc")
    Page<Task> findAllMyTask(Long id, Pageable pageable);

    @Query("from Task t left join fetch t.owner o left join fetch o.department left join fetch o.position left join fetch t.employee e left join fetch e.department left join fetch e.position left join fetch t.project p where t.project.id = :projectId and t.name like '%' || :name || '%' order by t.id desc")
    List<Task> findTaskByProjectId(String name, Long projectId);

    @Query("from Task t left join fetch t.owner o left join fetch o.department left join fetch o.position left join fetch t.employee e left join fetch e.department left join fetch e.position left join fetch t.project p where p.id = :id order by t.id desc")
    Page<Task> findAllProjectTask(Long id, PageRequest pageable);
}
