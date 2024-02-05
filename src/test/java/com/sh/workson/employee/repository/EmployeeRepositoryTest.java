package com.sh.workson.employee.repository;

import com.sh.workson.authority.entity.Authority;
import com.sh.workson.authority.entity.RoleAuth;
import com.sh.workson.authority.repository.AuthorityRepository;
import com.sh.workson.department.entity.Department;
import com.sh.workson.department.repository.DepartmentRepository;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.entity.WorkStatus;
import com.sh.workson.employee.repository.EmployeeRepository;
import com.sh.workson.position.entity.Position;
import com.sh.workson.position.repository.PositionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest // test에 관련된 bean만 로드하기 때문에 passwordencoder를 로드하지 못함
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Transactional(propagation = Propagation.NOT_SUPPORTED) // 트랜젝션 사용 X 설정
@SpringBootTest
class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @DisplayName("사원 등록 1명을 등록할 수 있다. (권한도 함께 저장되어야 한다.)")
    // integrity constraint (WORKS.FK_EMPLOYEE_POSITION_ID) violated - parent key not found - fk 되어있음
    @Test
    void test1(){
        //given
        // 인사총무(id 1) 선임(id 201)
        Employee employee = Employee.builder()
                .password(passwordEncoder.encode("1234"))
                .name("홍길동")
                .rest(0)
                .email("honggd@naver.com")
                .phone("010-2345-2345")
                .workStatus(WorkStatus.WORK)
                .position(Position.builder().id(201L).build())
                .department(Department.builder().id(1L).build())
                .build();

        // when
        employeeRepository.save(employee);
        Authority authority = Authority.builder().empId(employee.getId()).name(RoleAuth.EMP).build();
        authorityRepository.save(authority);

        // then
        assertThat(employee.getDepartment()).isNotNull();
        assertThat(employee.getPosition()).isNotNull();
        assertThat(employee.getId()).isNotNull();
        assertThat(authority.getId()).isNotNull();

    }

    @DisplayName("email 사원 조회시 권한정보도 함께 조회되어야한다.")
    @Test
    void test2() {
        Employee employee = Employee.builder()
                .password(passwordEncoder.encode("1234"))
                .name("신사임당")
                .rest(0)
                .email("sinsa@naver.com")
                .phone("010-1234-2345")
                .workStatus(WorkStatus.WORK)
                .position(Position.builder().id(201L).build())
                .department(Department.builder().id(1L).build())
                .build();
        employeeRepository.save(employee);
        Authority authority = Authority.builder().empId(employee.getId()).name(RoleAuth.EMP).build();
        authorityRepository.save(authority);

        // when
        Employee employee2 = employeeRepository.findByEmail(employee.getEmail());

        // then
        assertThat(employee2).isNotNull();
        assertThat(employee2.getAuthorities())
                .isNotEmpty()
                .allSatisfy((_authority) -> {
                    assertThat(_authority.getId()).isEqualTo(authority.getId());
                    assertThat(_authority.getEmpId()).isEqualTo(employee.getId());
                    assertThat(_authority.getName()).isEqualTo(authority.getName());
                });
    }
}