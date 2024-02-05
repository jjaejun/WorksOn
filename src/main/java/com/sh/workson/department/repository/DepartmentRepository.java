package com.sh.workson.department.repository;

import com.sh.workson.department.entity.Department;
import com.sh.workson.position.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
