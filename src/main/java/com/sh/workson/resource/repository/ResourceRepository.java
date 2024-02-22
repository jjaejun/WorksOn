package com.sh.workson.resource.repository;

import com.sh.workson.resource.entity.Resource;
import com.sh.workson.resource.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findByType(@Param("type") Type type);

    @Query("from Resource order by type desc")
    List<Resource> findByOrderByType();
}
