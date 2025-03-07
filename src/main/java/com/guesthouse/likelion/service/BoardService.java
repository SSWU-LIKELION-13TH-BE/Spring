package com.guesthouse.likelion.service;

import com.guesthouse.likelion.dto.BoardDTO;
import com.guesthouse.likelion.entity.Board;
import com.guesthouse.likelion.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final 이 붙은 필드만 생성자 생성
@Slf4j //log 찍을수 있게 해주는 어노테이션
//왜 pirnt 가 아니고 log?
//로그 레벨(Level) 제공 INFO, DEBUG, WARN, ERROR
//로그 파일(.log) 로 저장하여 운영 환경에서도 확인 가능 등 여러가지 사유로 print 대신 log 로 출력 찍어본다.
public class BoardService {
    private final BoardRepository boardRepository;
    private final S3Service s3Service;

    //들어온 boardId 값과 db의 boardId 값이 일치하는 row 가져오기
    public Optional<Board> getBoard(Long boardId) {
        return boardRepository.findByBoardId(boardId);
    }


    //게시글 하나 작성
    public void postBoard(Board board) {
         boardRepository.save(board);
    }

    //게시글 수정
    // 이부분 row 쿼리 안쓰고 처리하는 방법 없는지
    @Transactional
    public void putBoard(BoardDTO boardDTO){
                Board board = Board.builder()
                        .boardId(boardDTO.getBoardId())
                        .title(boardDTO.getTitle())
                        .content(boardDTO.getContent())
                        .writer(boardDTO.getWriter())
                        .postDate(new Date())
                        .build();

                //save 시 기존에 있는 객체라면 merge !
                //id를 전달하지 않을 경우 Insert 수행
                //id를 전달할 경우 해당 id에 대한 데이터가 있다면 Update 수행
                //id를 전달할 경우 해당 id에 대한 데이터가 없다면 Insert 수행
                boardRepository.save(board);

    }

    //게시글 삭제
    @Transactional //현재 쓰레드에 트렌젝션 없기때문에 remove 호출 신뢰 불가! 어노테이션으로 생성해줘야 한다.
    //이부분 더 설명 필요할듯
    public void deleteBoard(Long boardId){
        boardRepository.deleteByBoardId(boardId);
    }


    @Transactional
    //이미지 포함 게시글 생성
    public void ImageBoard(BoardDTO request) throws IOException {

        String savedImageURI = s3Service.upload(request.getImage()); //이미지 s3에 업로드하고 url 가져오기

        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .writer(request.getWriter())
                .image(savedImageURI) //img url 넣기
                .build();

        boardRepository.save(board);

    }
}
