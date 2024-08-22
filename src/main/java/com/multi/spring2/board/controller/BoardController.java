package com.multi.spring2.board.controller;

import com.multi.spring2.board.domain.Board;
import com.multi.spring2.board.domain.Page;
import com.multi.spring2.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Controller
@RestController //@Controller + @ResponseBody
@RequestMapping("/board")
@Slf4j
public class BoardController {
    private BoardService boardService;

    @Autowired
    public BoardController(@Qualifier("serviceimp2") BoardService boardService) {
        this.boardService = boardService;
    }

    public BoardController() {
        System.out.println("BoardController created");
    }

    @GetMapping
    public String board() {
        return "board/board";
    }

    @PostMapping("")
    public ResponseEntity write(@RequestBody Board b ) {
        boardService.write(b);
        return ResponseEntity.status(HttpStatus.CREATED).build();
//       return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping({"/list", //optCp가 1, optWord가 ""
            "/list/{optCp}",         //optWord가 ""
            "/list/{optCp}/{optWord}"
    })
    public ResponseEntity<com.multi.spring2.board.domain.Page<Board>> list(
            @PathVariable(value = "optCp", required = false) Optional<Integer> optCp,
            @PathVariable(value = "optWord", required = false) Optional<String> optWord) {
        int currentPage = optCp.orElse(1);
        String word = optWord.orElse("");
        int pageSize = 5; //페이지별 보여줄 목록수
        Page<Board> page = boardService.list(currentPage, pageSize, word);
        return ResponseEntity.ok(page);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<Board> detail(@PathVariable int boardNo) {
        Board board = boardService.detail(boardNo);
        return ResponseEntity.ok(board);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("")
    public ResponseEntity modify(@RequestBody Board b) throws Exception{
        boardService.modify(b);
        return ResponseEntity.status(HttpStatus.CREATED).build(); //201
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @DeleteMapping("/{boardNo}")
    public ResponseEntity delete(@PathVariable int boardNo) {
        boardService.remove(boardNo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}