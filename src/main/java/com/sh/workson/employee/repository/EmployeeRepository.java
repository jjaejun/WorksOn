package com.sh.workson.employee.repository;

import com.sh.workson.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * <객체타입, ID타입>
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /**
     * email로 로그인 시, 권한 정보도 저장할 수 있어야한다.
     */
    @Query("from Employee e join fetch e.authorities join fetch e.department join fetch e.position where e.email = :email")
    Employee findByEmail(String email);


    /**
     * 민정
     */
    @Query("from Employee e left join fetch e.department left join fetch e.position where e.name like '%' || :name || '%'")
    List<Employee> findByName(String name);





    /**
     * 재준
     */









    /**
     * 우진
     */







    /**
     * 민준
     */








    /**
     * 준희
     */









    /**
     * 무진
     */










}
