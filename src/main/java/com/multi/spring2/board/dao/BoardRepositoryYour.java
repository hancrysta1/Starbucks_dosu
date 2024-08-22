package com.multi.spring2.board.dao;

import com.multi.spring2.board.domain.Board;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Qualifier("your")
public class BoardRepositoryYour implements BoardRepository {
    @Override
    public void save(Board board) {

        System.out.println("BoardRepositoryYour save()");
    }

    @Override
    public List<Board> findAll() {
        return List.of();
    }

    @Override
    public Board findById(int boardNo) {
        return null;
    }
    @Override
    public List<Board> findByWord(String word){return null;}
    @Override
    public void update(Board board) {

    }

    @Override
    public void delete(int boardNo) {

    }
}
