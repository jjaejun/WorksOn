package com.sh.workson.project.repository;

import com.sh.workson.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findAll(Pageable pageable);

    /**
     * test에서만 사용하는 쿼리
     * @param id
     * @return
     */
    @Query("from Project p join fetch p.employee e join fetch e.department join fetch e.position where p.id = :id order by p.id desc")
    List<Project> findByProjectId(long id);

    @Query("from Project p join fetch p.projectEmployees pe join fetch p.employee e join fetch e.department join fetch e.position where pe.employee.id = :id and p.status != 'DONE' order by p.id desc ")
    Page<Project> findByEmpId(Long id, Pageable pageable);

    @Query("from Project p join fetch p.employee e join fetch e.department join fetch e.position where e.id = :id and p.status != 'DONE' order by p.id desc ")
    Page<Project> findByOwnerId(Long id, Pageable pageable);


    @Query("from Project p left join fetch p.employee e left join fetch e.department left join fetch e.position left join fetch p.tasks where p.id = :id order by p.id desc")
    Optional<Project> findById(Long id);
}
