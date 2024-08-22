package com.multi.spring2.board.mapper;

import com.multi.spring2.board.domain.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    void insert(Board board);

    List<Board> selectAll();

    Board selectById(int boardNo);

    List<Board> selectByWord(String word);

    int delete(int boardNo);


    int update(Board board);
    int count(String word);
    List<Board> selectByPage(Map<String, Object> map);












}
