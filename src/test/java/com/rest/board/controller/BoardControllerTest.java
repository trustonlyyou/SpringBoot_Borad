package com.rest.board.controller;

import com.rest.board.entity.Board;
import com.rest.board.repository.BoardRepository;
import com.rest.board.service.BoardService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Test
    public void createBoard() {
        Board board = new Board();
        board.setUsername("LungHwan");
        board.setTitle("제목");
        board.setContent("내용");
        board.setRegTime(LocalDateTime.now());
        board.setUpdateTime(LocalDateTime.now());

        Board save = boardRepository.save(board);

        System.out.println(save.toString());
    }

    @Test
    public void getBoardList() throws Exception {
        List<Board> result = boardService.getBoard();

        mockMvc.perform(get("/api/boards/all")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }
}