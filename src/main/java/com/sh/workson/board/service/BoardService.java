package com.sh.workson.board.service;

import com.sh.workson.board.dto.BoardListDto;
import com.sh.workson.board.entity.Board;
import com.sh.workson.board.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Page<BoardListDto> findAll(Pageable pageable) {
        Page<Board> boardPage =  boardRepository.findAll(pageable);
        return boardPage.map((board) -> convertToBoardListDto(board));
    }

    private BoardListDto convertToBoardListDto(Board board) {
        BoardListDto boardListDto = modelMapper.map(board, BoardListDto.class);
        boardListDto.setEmpId(
                Optional.ofNullable(board.getEmployee())
                        .map((employee) -> employee.getName())
                        .orElse(null)
                );
        boardListDto.setAttachCount(board.getAttachments().size());
        return boardListDto;
    }
}
