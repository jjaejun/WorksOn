package com.sh.workson.resource.repository;

import com.sh.workson.resource.entity.Resource;
import com.sh.workson.resource.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findByType(Type type);
}
