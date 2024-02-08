package com.sh.workson.attend.repository;

import com.sh.workson.attend.entity.Attend;
import com.sh.workson.attend.entity.State;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AttendRepositoryTest {
    @Autowired
    AttendRepository attendRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @DisplayName("사용자는 출퇴근시간을 볼 수 있다.")
    @Test
    @Transactional
    void test1(){
        Employee employee = Employee.builder().id(952L).build();
        Attend attend = Attend.builder()
                .startAt(LocalDateTime.of(2024, 2, 6, 8, 58, 10))
                .endAt(LocalDateTime.of(2024,2,6,17,58,22))
                .state(State.QUIT)
                .employee(employee)
                .build();
        attendRepository.save(attend);
        assertThat(attend.getId()).isNotNull();

        Attend attend2 = attendRepository.findByAttendId(attend.getId());
        System.out.println(attend2);
        assertThat(attend2)
                .isNotNull()
                .satisfies((_attend -> {
                    assertThat(_attend.getStartAt()).isNotNull();
                    assertThat(_attend.getEndAt()).isNotNull();
                    assertThat(_attend.getState()).isNotNull();
                    assertThat(_attend.getEmployee()).isNotNull();
                }));
    }

    @DisplayName("사용자는 내 근태 현황을 볼 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    @Transactional
    void test2(int pageNumber) {
        // given
        final int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        // when
        Page<Attend> pageResult = attendRepository.findAll(pageable);
        System.out.println(pageResult);
        // then
        assertThat(pageResult.getContent()).isNotEmpty();
        assertThat(pageResult.getNumber()).isEqualTo(pageNumber);
        assertThat(pageResult.getNumberOfElements()).isLessThanOrEqualTo(pageSize);

    }
}
