package com.sh.workson.board.repository;

import com.sh.workson.board.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {


    List<BoardComment> findByBoardId(@Param("boardId") Long boardId);



}



