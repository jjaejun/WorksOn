package com.sh.workson.attend.repository;

import com.sh.workson.attend.entity.Attend;
import com.sh.workson.attend.entity.State;
import com.sh.workson.employee.entity.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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

    @DisplayName("사용자는 출퇴근시간을 볼 수 있다.")
    @Test
    @Transactional
    void test1(){
        Employee employee = Employee.builder().email("honggd@naver.com").build();
        Attend attend = Attend.builder()
                .startAt(LocalDateTime.of(2024, 2, 6, 8, 58, 10))
                .endAt(LocalDateTime.of(2024,2,6,17,58,22))
                .state(State.QUIT)
                .build();
        attendRepository.save(attend);
        assertThat(attend.getId()).isNotNull();
    }



}
