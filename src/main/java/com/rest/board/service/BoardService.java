package com.rest.board.service;

import com.rest.board.entity.Board;
import com.rest.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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
            log.error("error :: '{}'", e);
            return Collections.emptyList();
        }

        return getBoardList;
    }
}
