package com.sh.workson.board.service;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.attachment.entity.AttachType;
import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.attachment.repository.AttachmentRepository;
import com.sh.workson.attachment.service.AttachmentService;
import com.sh.workson.board.dto.BoardCreateDto;
import com.sh.workson.board.dto.BoardDetailDto;
import com.sh.workson.board.dto.BoardListDto;
import com.sh.workson.board.dto.BoardUpdateDto;
import com.sh.workson.board.entity.Board;
import com.sh.workson.board.entity.Type;
import com.sh.workson.board.repository.BoardRepository;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
@Slf4j
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;


    public Page<BoardListDto> findAll(Pageable pageable) {
        Page<Board> boardPage = boardRepository.findAll(pageable);
        Page<BoardListDto> boardListDtos = boardPage.map((board) -> convertToBoardListDto(board));

        for(int i = 0; i < boardPage.get().toList().size(); i ++){
            boardListDtos.get().toList().get(i).setAttachCount(attachmentRepository.findByBoardId(boardListDtos.get().toList().get(i).getId()).size());
        }
        return boardListDtos;
    }



    private BoardListDto convertToBoardListDto(Board board) {
        BoardListDto boardListDto = modelMapper.map(board, BoardListDto.class);
        boardListDto.setEmpName(
                Optional.ofNullable(board.getEmployee())
                        .map((employee) -> employee.getName())
                        .orElse(null)
        );
        boardListDto.setEmpId(board.getEmployee().getId());
        boardListDto.setAttachCount(board.getAttachments().size());
        return boardListDto;
    }

    public void createBoard(BoardCreateDto boardCreateDto) {
        log.debug("boardCreateDto = {}", boardCreateDto);

        Board board = boardRepository.save(convertToBoard(boardCreateDto));

        log.debug("board = {}", board);
//        boardCreateDto.getAttachments().forEach((attachmentCreateDto -> {
//            attachmentCreateDto.setBoardId(board.getId());
//            attachmentService.createAttachment(attachmentCreateDto);
//        }));
        if(!boardCreateDto.getAttaches().isEmpty()) {
            for (AttachmentCreateDto attach: boardCreateDto.getAttaches()) {
                attach.setBoardId(board.getId());
                attach.setEmployee(board.getEmployee());
                Attachment attachment = modelMapper.map(attach, Attachment.class);
                log.debug("attachment = {}", attachment);
                attachmentRepository.save(attachment);
            }
        }

    }

    private Board convertToBoard(BoardCreateDto boardCreateDto) {
//        Board board = Board.builder()
//                .title(boardCreateDto.getTitle())
//                .content(boardCreateDto.getContent())
//                .type(boardCreateDto.getType())
//                .build();
//        board.setEmployee(employeeRepository.findById(boardCreateDto.getEmpId()).orElseThrow());
        return modelMapper.map(boardCreateDto, Board.class);
    }

    public BoardDetailDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        System.out.println(board);
        return convertToBoardDetailDto(board);
//                .map((board) -> convertToBoardDetailDto(board))
//                .orElseThrow(); // NoSuchElementException 던짐    }
    }


    private BoardDetailDto convertToBoardDetailDto(Board board) {
        BoardDetailDto boardDetailDto = BoardDetailDto.builder()
                .id(board.getId())
                .type(board.getType())
                .title(board.getTitle())
                .employee(board.getEmployee())
                .content(board.getContent())
                .viewCount(board.getViewCount())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .attachments(board.getAttachments())
                .build();

        return boardDetailDto;
    }


    @Transactional
    public int updateView(Long id) {
        return boardRepository.updateView(id);
    }




    public Page<BoardListDto> findByType(Type type, Pageable pageable) {
        Page<Board> boardPage = boardRepository.findByType(type, pageable);
        Page<BoardListDto> boardListDtos = boardPage.map((board) -> convertToBoardListDto(board));

        for(int i = 0; i < boardPage.get().toList().size(); i ++){
            boardListDtos.get().toList().get(i).setAttachCount(attachmentRepository.findByBoardId(boardListDtos.get().toList().get(i).getId()).size());
        }
        return boardListDtos;
    }



    public BoardUpdateDto update(BoardUpdateDto boardUpdateDto) {
        // DTO를 엔티티로 변환
        Board boardUpdate = boardRepository.findById(boardUpdateDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. ID: " + boardUpdateDto.getId()));

        // 엔티티에 DTO에서 받아온 정보를 업데이트
        boardUpdate.setId(boardUpdateDto.getId());
        boardUpdate.setTitle(boardUpdateDto.getTitle());
        boardUpdate.setContent(boardUpdateDto.getContent());

        // 업데이트된 엔티티 저장
        Board updatedBoard = boardRepository.save(boardUpdate);

        // 엔티티를 DTO로 변환하여 반환
        return convertToDto(updatedBoard);
    }


    // 엔티티를 DTO로 변환하는 메서드
    private BoardUpdateDto convertToDto(Board board) {
        BoardUpdateDto boardUpdateDto = new BoardUpdateDto();
        boardUpdateDto.setId(board.getId());
        boardUpdateDto.setTitle(board.getTitle());
        boardUpdateDto.setContent(board.getContent());
        return boardUpdateDto;
    }

    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
    }
}
