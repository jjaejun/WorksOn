package com.sh.workson.board.service;
import com.sh.workson.board.dto.BoardCommentDto;
import com.sh.workson.board.entity.Board;
import com.sh.workson.board.entity.BoardComment;
import com.sh.workson.board.repository.BoardCommentRepository;
import com.sh.workson.board.repository.BoardRepository;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class BoardCommentService {

    @Autowired
    private BoardCommentRepository boardCommentRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public void insertComment(BoardCommentDto boardCommentDto, Long boardId, Long employeeId) {
        // 게시글 ID를 이용해 게시글을 가져옵니다.
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다: " + boardId));

        // 직원 ID를 이용해 직원 정보를 조회합니다.
        Employee author = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("직원을 찾을 수 없습니다: " + employeeId));

        // 새로운 댓글 객체를 생성합니다.
        BoardComment newComment = new BoardComment();
        newComment.setContent(boardCommentDto.getContent());
        newComment.setEmployee(author);
        newComment.setBoard(board);
        log.debug("board = {}" , boardCommentDto);

        // 새로운 댓글을 저장합니다.
        boardCommentRepository.save(newComment);
    }

    public List<BoardCommentDto> findByBoardId(Long id) {
        List<BoardComment> comments = boardCommentRepository.findByBoardId(id);
        return comments.stream()
                .map(comment -> {
                    BoardCommentDto dto = new BoardCommentDto();
                    dto.setEmployeeId(comment.getId());
                    dto.setContent(comment.getContent());
                    dto.setId(comment.getEmployee().getName());
                    dto.setCreatedAt(comment.getCreatedAt());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}



