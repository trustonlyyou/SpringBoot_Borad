package com.rest.board.controller;

import com.rest.board.constant.BoardStatus;
import com.rest.board.entity.Board;
import com.rest.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@Slf4j
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 모든 게시물
    @GetMapping(
            value = "/all",
            consumes = MediaType.APPLICATION_JSON_VALUE, // 들어오는 타입 정의
            produces = MediaType.APPLICATION_JSON_VALUE // 나가는 타입 정의
    )
    public ResponseEntity get() {
        List<Board> result = boardService.getBoard();

        if (result.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping(
            value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity add(@RequestBody @Valid Board board,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();

            for (ObjectError error : errors) {
                log.error(error.getDefaultMessage());
            }

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            boardService.add(board);

            return ResponseEntity.ok("게시물 등록 완료");
        }
    }

    @PutMapping(value = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity edit(@RequestBody @Valid Board board, @PathVariable("id") Long id) {
        BoardStatus status = boardService.edit(id, board);

        if (BoardStatus.FAIL.equals(status)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok("게시물 수정 완료");
        }
    }

    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        BoardStatus status = boardService.delete(id);

        if (BoardStatus.FAIL.equals(status)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok("게시물 삭제 완료");
        }
    }
}
