package com.multi.spring2.board.service;

import com.multi.spring2.board.domain.Board;
import com.multi.spring2.board.domain.Page;

import java.util.List;

public interface BoardService {
    public void write(Board b);
    public List<Board> list();
    public List<Board> search(String word);
    public Board detail(int boardNo);
    public void modify(Board b) throws Exception;
    public void remove(int boardNo);

    public Page<Board> list(int currentPage, int pageSize, String word);
}
