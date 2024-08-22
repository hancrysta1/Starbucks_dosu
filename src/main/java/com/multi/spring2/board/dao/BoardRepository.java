package com.multi.spring2.board.dao;

import com.multi.spring2.board.domain.Board;

import java.util.List;

public interface BoardRepository {
    public void save(Board board);
    public List<Board> findAll();
    public Board findById(int boardNo);
    public List<Board> findByWord(String word);
    public void update(Board board);
    public void delete(int boardNo);
}
