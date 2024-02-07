package com.sh.workson.approval.repository;

import com.sh.workson.approval.entity.Approval;
import com.sh.workson.department.repository.DepartmentRepository;
import com.sh.workson.employee.repository.EmployeeRepository;
import com.sh.workson.position.repository.PositionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApprovalRepositoryTest {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ApprovalRepository approvalRepository;

    @DisplayName("전자결재 여러건 조회")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @Transactional
    void test1(int pageNumber) {

        // given
        final int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // when
        Page<Approval> pageResult = approvalRepository.findAll(pageable);
        System.out.println(pageResult);

        // then
        assertThat(pageResult.getContent()).isNotEmpty();
        assertThat(pageResult.getNumber()).isEqualTo(pageNumber);
        assertThat(pageResult.getNumberOfElements()).isLessThanOrEqualTo(pageSize);
    }
}
