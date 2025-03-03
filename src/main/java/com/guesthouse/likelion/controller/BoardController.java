package com.guesthouse.likelion.controller;

import com.guesthouse.likelion.dto.BoardDTO;
import com.guesthouse.likelion.entity.Board;
import com.guesthouse.likelion.service.BoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/api/Comment")
@RequiredArgsConstructor
@Tag(name = "Community Controller", description = "게시판 관련 API")
//tag 어노테이션을 통해서 명세에 이름을 붙일 수 있다.
public class BoardController {

    private final BoardService boardService;

    // 게시글 하나 띄우기
    @GetMapping("/getBoard")
    public Optional<Board> getBoard(@RequestParam(name = "boardId") Long boardId) {
        return boardService.getBoard(boardId);
    }

    // 게시글 작성하기
    @PostMapping("/postBoard")
    public void postBoard(@RequestBody BoardDTO boardDTO) {
         Board board = Board.builder()
                 .title(boardDTO.getTitle())
                 .content(boardDTO.getContent())
                 .writer(boardDTO.getWriter())
                 .build();
         boardService.postBoard(board);
    }

    // 게시글 수정하기
    @PutMapping("/putBoard")
    public void putBoard(@RequestBody BoardDTO boardDTO) {
        boardService.putBoard(boardDTO);
    }

    // 게시글 삭제하기
    @DeleteMapping("/deleteBoard")
    public void deleteBoard(@PathVariable Long boardId){
        boardService.deleteBoard(boardId);
    }
}
