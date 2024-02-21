package com.sh.workson.board.repository;

import com.sh.workson.board.entity.Board;
import com.sh.workson.board.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("from Board b left join fetch b.employee e where b.type = :type order by b.id desc")
    Page<Board> findByType(@Param("type") Type type, Pageable pageable);


    @Query("from Board b join fetch b.employee e order by b.id desc")
    Page<Board> findAll(Pageable pageable);




    @Modifying
    @Query("update Board b set b.viewCount = b.viewCount + 1 where b.id = :id")
    int updateView(@Param("id") Long id);

    @Query("from Board b left join fetch b.employee e left join fetch b.attachments a where b.id = :id")
    Optional<Board> findById(Long id);

}
