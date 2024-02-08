package com.sh.workson.board.controller;

import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.board.dto.BoardCreateDto;
import com.sh.workson.board.dto.BoardListDto;
import com.sh.workson.board.entity.Type;
import com.sh.workson.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boardList.do")
    public void boardList(@PageableDefault(size = 5, page = 0) Pageable pageable, Model model) {
        log.info("boardService = {}", boardService.getClass());

        log.debug("pageable = {}", pageable);
        Page<BoardListDto> boardPage = boardService.findAll(pageable);
        log.debug("boards = {}", boardPage.getContent());
        model.addAttribute("boards", boardPage.getContent());
        model.addAttribute("totalCount", boardPage.getTotalElements()); //전체 게시물수
    }

    @GetMapping("/createBoard.do")
    public void createBoard() {
    }

    @PostMapping("/createBoard.do")
    public String createBoard(
            @Valid BoardCreateDto boardCreateDto,
            BindingResult bindingResult,
            @RequestParam("upFile") List<MultipartFile> upFiles,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            RedirectAttributes redirectAttributes)
            throws IOException {
        if (bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }


        // 첨부파일 S3에 저장
//        for (MultipartFile upFile: upFiles) {
//            if (upFile.getSize() > 0) {
//                com.sh.workson.attachment.dto.AttachmentCreateDto attachmentCreateDto = s3FileService.upload(upFile);
//                boardCreateDto.addAttachmentCreateDto(attachmentCreateDto);
//            }
//        }
//
        // DB에 저장(게시글, 첨부파일)

        boardCreateDto.setEmployee(employeeDetails.getEmployee());
        boardService.createBoard(boardCreateDto);



        //리다이렉트후에 사용자 피드백
        redirectAttributes.addFlashAttribute("msg", "게시글을 성공적으로 등록했습니다");
        return "redirect:/board/boardList.do";
    }

}
