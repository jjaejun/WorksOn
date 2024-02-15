package com.sh.workson.board.controller;

import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.board.dto.BoardCommentCreateDto;
import com.sh.workson.board.service.BoardCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/comment")
public class BoardCommentController {
    @Autowired
    public BoardCommentService boardCommentService;

    @PostMapping("/CreateComment.do")
    public ResponseEntity<?> createComment(
            @RequestParam("boardId") Long boardId,
            @RequestParam("content") String content,
            @RequestParam("parentCommentId") Long parentId,
            @RequestParam("commentLevel") int commentLevel,
            @AuthenticationPrincipal EmployeeDetails employeeDetails
            ){
        BoardCommentCreateDto commentCreateDto = new BoardCommentCreateDto(boardId, content, parentId, commentLevel, employeeDetails.getEmployee().getId());
        log.debug("commentCreateDto = {}", commentCreateDto);

        boardCommentService.createComment(commentCreateDto);

        return new ResponseEntity<>("대댓글이 등록되었습니다.", HttpStatus.OK);
    }



}
