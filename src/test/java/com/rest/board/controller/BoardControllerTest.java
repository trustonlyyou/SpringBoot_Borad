package com.rest.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.board.builder.BoardParam;
import com.rest.board.entity.Board;
import com.rest.board.repository.BoardRepository;
import com.rest.board.service.BoardService;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Autowired
    private ObjectMapper objectMapper;

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
    @DisplayName("게시물 리스트 가져오기")
    public void getBoardList() throws Exception {
        List<Board> result = boardService.getBoard();

        mockMvc.perform(get("/api/boards/all")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("게시물 추가")
    public void addBoard() throws Exception {

        Board board = new Board();
        board.setUsername("Junit");
        board.setTitle("Junit Test Title");
        board.setContent("Junit Test Content");

        String request = objectMapper.writeValueAsString(board);

        mockMvc.perform(post("/api/boards/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("게시물 수정")
    public void edit() throws Exception {
        Board board = new Board();
        board.setUsername("LungHwan");
        board.setContent("내용 수정");
        board.setTitle("제목 수정");

        String request = objectMapper.writeValueAsString(board);

        mockMvc.perform(put("/api/boards/edit/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(request))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("게시물 삭제")
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/boards/delete/2")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }
}