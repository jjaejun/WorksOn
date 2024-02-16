package com.sh.workson.department.service;

import com.sh.workson.department.entity.Department;
import com.sh.workson.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }
}
