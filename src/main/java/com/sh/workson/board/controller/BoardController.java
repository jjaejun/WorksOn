package com.sh.workson.board.controller;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.attachment.dto.AttachmentDetailDto;
import com.sh.workson.attachment.entity.AttachType;
import com.sh.workson.attachment.repository.AttachmentRepository;
import com.sh.workson.attachment.service.AttachmentService;
import com.sh.workson.attachment.service.S3FileService;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.board.dto.*;
import com.sh.workson.board.entity.Board;
import com.sh.workson.board.entity.BoardComment;
import com.sh.workson.board.entity.Type;
import com.sh.workson.board.service.BoardCommentService;
import com.sh.workson.board.service.BoardService;
import com.sh.workson.employee.entity.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private S3FileService s3FileService;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private BoardCommentService boardCommentService;

    @GetMapping("/boardAllList.do")
    public String boardAllList(){
        return null;
    }

    @GetMapping("/boardList.do")
    public void boardList(@RequestParam(name = "type", required = false) String type,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "2") int size,
                          @RequestParam(name = "page2", defaultValue = "0") int page2,
                          @RequestParam(name = "size2", defaultValue = "2") int size2,
                          Model model) {
        log.info("Fetching board list. Type: {}", type);

        Page<BoardListDto> boardNotiPage = boardService.findByType(Type.valueOf(type), PageRequest.of(page,size));;


        log.debug("Boards: {}", boardNotiPage.getContent());

        model.addAttribute("boardNoti", boardNotiPage.getContent());
        model.addAttribute("notiTotalCount", boardNotiPage.getTotalElements()); //전체 게시물수
        model.addAttribute("notiSize", boardNotiPage.getSize());
        model.addAttribute("notiNumber", boardNotiPage.getNumber());
        model.addAttribute("notiTotalpages", boardNotiPage.getTotalPages());
        model.addAttribute("type", type);
        log.debug("size = {}", boardNotiPage.getSize());
        log.debug("number = {}", boardNotiPage.getNumber());
        log.debug("totalpages = {}", boardNotiPage.getTotalPages());


    }

    @GetMapping("/createBoard.do")
    public void createBoard() {
    }

    @PostMapping("/createBoard.do")
    public String createBoard(
            @Valid BoardCreateDto boardCreateDto,
            BindingResult bindingResult,
            @RequestParam( name = "files") List<MultipartFile> files,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            RedirectAttributes redirectAttributes)
            throws IOException {
        if (bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        // 첨부파일 S3에 저장
        for(MultipartFile file : files) {
            log.debug("file = {}", file);
            if(file.getSize() > 0){
                AttachmentCreateDto attachmentCreateDto = s3FileService.upload(file, AttachType.BOARD);
                log.debug("attachmentCreateDto = {}", attachmentCreateDto);
                boardCreateDto.addAttachmentCreateDto(attachmentCreateDto);
            }
        }

        // DB에 저장(게시글, 첨부파일)
        boardCreateDto.setEmployee(employeeDetails.getEmployee());
        boardService.createBoard(boardCreateDto);

        //리다이렉트후에 사용자 피드백
        redirectAttributes.addFlashAttribute("msg", "게시글을 성공적으로 등록했습니다");
        return "redirect:/board/boardList.do?type=" + boardCreateDto.getType();
    }

    @GetMapping("/boardDetail.do")
    public void boardDetail(@RequestParam("id") Long id , Model model){

        BoardDetailDto boardDetailDto = boardService.findById(id);
        List<BoardCommentDto> commentList = boardCommentService.findByBoardId(id);
        model.addAttribute("board", boardDetailDto);
        model.addAttribute("commentList", commentList);
        log.debug("board = {}", boardDetailDto);
        log.debug("commentList = {}", commentList);

        // 조회수 증가
        boardService.updateView(id);
    }


    @PostMapping("/boardDetail.do")
    public String insertComment(
            @RequestParam("id") Long id,
            @RequestParam("comment") String comment,
//            @RequestParam("parentId") Long parentId,
            Authentication authentication) {
        EmployeeDetails employeeDetails = (EmployeeDetails) authentication.getPrincipal();
        Long employeeId = employeeDetails.getEmployee().getId();

        log.debug("id = {}" , id);
        log.debug("comment = {}" , comment);
//        log.debug("parentId = {}" , parentId);

        BoardCommentDto boardCommentDto = new BoardCommentDto();
        boardCommentDto.setContent(comment);
        boardCommentDto.setParentComment(BoardComment.builder().build());
        log.debug("board = {}" , boardCommentDto);

        boardCommentService.insertComment(boardCommentDto, id, employeeId);
        log.debug("board = {}" , boardCommentDto);

        return "redirect:/board/boardDetail.do?id=" + id;
    }


    @GetMapping("/fileDownload.do")
    public ResponseEntity<?> fileDownload(@RequestParam("id") Long id)
            throws UnsupportedEncodingException {

        // 파일다운로드 업무로직
        AttachmentDetailDto attachmentDetailDto = attachmentService.findById(id);
        return s3FileService.download(attachmentDetailDto);

    }



    @PostMapping("/boardUpdate.do")
    public String update(BoardUpdateDto boardUpdateDto,
                         Model model,
                         @RequestParam("id") Long id,
                         RedirectAttributes redirectAttributes) {
        log.debug("boardUpdateDto = {}" , boardUpdateDto);
        BoardUpdateDto boardUpdateDto1  = boardService.update(boardUpdateDto);
        model.addAttribute("boardUpdateDto", boardUpdateDto1);

        redirectAttributes.addFlashAttribute("msg", "게시글 수정이 완료되었습니다");

        return "redirect:/board/boardDetail.do?id=" + id;
    }

    @PostMapping("/boardDelete.do")
    public String deleteBoard(@RequestParam("boardId") Long id) {
        boardService.deleteBoardById(id);
        return "redirect:/board/boardList.do";
    }





}
