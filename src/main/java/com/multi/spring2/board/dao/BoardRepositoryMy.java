package com.multi.spring2.board.dao;

import com.multi.spring2.board.domain.Board;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("my")
@Slf4j
public class BoardRepositoryMy implements BoardRepository {
//    private static final org.slf4j.Logger slf4jLogger =
//            LoggerFactory.getLogger(BoardRepositoryMy.class);

    private static final org.apache.log4j.Logger log4jLogger =
            org.apache.log4j.Logger.getLogger(BoardRepositoryMy.class);

    private List<Board> boards;

    public BoardRepositoryMy() {
        boards = new ArrayList<Board>();
    }
    @Override
    public void save(Board board) {
        System.out.println("BoardRepositoryMy save()");
        log4jLogger.warn("warn : This is message from Log4J");
        log4jLogger.info("info : This is message from Log4J");
        log4jLogger.debug("debug : This is message from Log4J");


//        slf4jLogger.warn("warn : This is message from SLF4J");
//        slf4jLogger.info("info : This is message from SLF4J");
//        slf4jLogger.debug("debug : This is message from SLF4J");

        //@Slf4J어노테이션용 변수 : log
        log.warn("warn : This is message from SLF4J");
        log.info("info : This is message from SLF4J");
        log.debug("debug : This is message from SLF4J");

        Optional<Board> maxBoard = boards.stream().max(Comparator.comparingInt(Board::getBoardNo));
            if(maxBoard.isPresent()){
                board.setBoardNo(maxBoard.get().getBoardNo()+1);
            }else{
                board.setBoardNo(1);
            }
            boards.add(board);
            System.out.println(boards);

    }

    @Override
    public List<Board> findAll() {
        return boards;
    }

    @Override
    public Board findById(int boardNo) {
        for(Board b : boards) {
            if(b.getBoardNo() == boardNo) {
                return b;
            }
        }
        return null;
    }
    @Override
    public List<Board> findByWord(String word){return null;}
    @Override
    public void update(Board board) {
        for(Board b : boards) {
            if(b.getBoardNo() == board.getBoardNo()) {
                b = board;
            }
        }
    }

    @Override
    public void delete(int boardNo) {
        for(Board b : boards) {
            if(b.getBoardNo() == boardNo) {
                boards.remove(b);
            }
        }
    }
}
