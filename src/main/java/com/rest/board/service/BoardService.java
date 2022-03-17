package com.rest.board.service;

import com.rest.board.constant.BoardStatus;
import com.rest.board.entity.Board;
import com.rest.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getBoard() {
        List<Board> getBoardList = new ArrayList<>();

        try {
            getBoardList = boardRepository.findAll();
        } catch (Exception e) {
            log.error("error :: '{}'", e.getMessage());
            return Collections.emptyList();
        }

        return getBoardList;
    }

    @Transactional(
            isolation = Isolation.READ_UNCOMMITTED,
            rollbackFor = {Exception.class, RuntimeException.class}
    )
    public void add(Board params) {
        Board board = new Board();
        board.setUsername(params.getUsername());
        board.setTitle(params.getTitle());
        board.setContent(params.getContent());
        board.setRegTime(LocalDateTime.now());
        board.setUpdateTime(LocalDateTime.now());

        boardRepository.save(board);
        log.info("add success");
    }

    @Transactional(
            isolation = Isolation.READ_UNCOMMITTED,
            rollbackFor = {Exception.class, RuntimeException.class}
    )
    public BoardStatus edit(Long id, Board params) {
        Board board = boardRepository.getById(id);

        if (board == null) {
            log.error("board is null");
            return BoardStatus.FAIL;
        } else {
            board.setContent(params.getContent());
            board.setTitle(params.getTitle());
            boardRepository.save(board);
            log.info("edit success");
            return BoardStatus.SUCCESS;
        }
    }

    @Transactional(
            isolation = Isolation.READ_UNCOMMITTED,
            rollbackFor = {Exception.class, RuntimeException.class}
    )
    public BoardStatus delete(Long id) {
        Board board = boardRepository.getById(id);

        if (board == null) {
            log.error("board is null");
            return BoardStatus.FAIL;
        } else {
            boardRepository.deleteById(id);
            log.info("delete success");
            return BoardStatus.SUCCESS;
        }
    }

}
