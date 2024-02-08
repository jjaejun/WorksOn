package com.sh.workson.board.controller;

import com.sh.workson.board.dto.BoardListDto;
import com.sh.workson.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boardList.do")
    public void boardList(@PageableDefault(size = 5, page = 0) Pageable pageable , Model model){
        log.info("boardService = {}" , boardService.getClass());

        log.debug("pageable = {}" , pageable);
        Page<BoardListDto> boardPage = boardService.findAll(pageable);
        log.debug("boards = {}" , boardPage.getContent());
        model.addAttribute("boards", boardPage.getContent());
        model.addAttribute("totalCount", boardPage.getTotalElements()); //전체 게시물수
    }

    @GetMapping("/createBoard.do")
    public void createBoard(){}



}
