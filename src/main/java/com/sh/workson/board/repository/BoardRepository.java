package com.sh.workson.board.repository;

import com.sh.workson.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("from Board b left join fetch  b.employee m left join fetch b.attachments order by b.id desc")
    Page<Board> findAll(Pageable pageable);
}
