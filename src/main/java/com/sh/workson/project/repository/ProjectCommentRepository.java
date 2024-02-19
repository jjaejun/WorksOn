package com.sh.workson.project.repository;

import com.sh.workson.project.entity.ProjectComment;
import com.sh.workson.project.entity.ProjectCommentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectCommentRepository extends JpaRepository<ProjectComment, Long> {
    @Query("from ProjectComment pc join fetch pc.employee e join fetch e.position join fetch e.department where pc.typeId = :id and pc.type = :projectCommentType order by pc.createdAt")
    List<ProjectComment> findByTypeId(Long id, ProjectCommentType projectCommentType);
}
