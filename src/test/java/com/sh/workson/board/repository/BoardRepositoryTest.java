package com.sh.workson.board.repository;

import com.sh.workson.board.entity.Board;
import com.sh.workson.board.entity.Type;
import com.sh.workson.employee.entity.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED )
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;

    @DisplayName("게시글 한건 등록")
    @Test
    void test1() {
        // given
        Employee employee = Employee.builder().id(151L).build();
        Board board = Board.builder()
                .title("천무진")
                .type(Type.notification)
                .employee(employee)
                .content("내용")
                .build();

        // when
        boardRepository.save(board);

        // then
        assertThat(board.getId()).isNotNull().isNotZero();
    }

    @DisplayName("게시글 여러 건 조회")
    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    void test2(int pageNumber) {
        // given

        final int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        // when
        Page<Board> pageResult = boardRepository.findAll(pageable);
        System.out.println(pageResult);

        // then
        assertThat(pageResult.getContent()).isNotEmpty();
        assertThat(pageResult.getNumber()).isEqualTo(pageNumber);
        assertThat(pageResult.getNumberOfElements()).isLessThanOrEqualTo(pageSize);
    }





}

