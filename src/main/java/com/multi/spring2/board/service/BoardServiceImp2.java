package com.multi.spring2.board.service;

import com.multi.spring2.board.domain.Board;
import com.multi.spring2.board.domain.Page;
import com.multi.spring2.board.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("serviceimp2")
@Slf4j
public class BoardServiceImp2 implements BoardService{
    private SqlSessionFactory sessionFactory;

    @Autowired
    public BoardServiceImp2(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void write(Board b) {
        SqlSession session = sessionFactory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);
        mapper.insert(b);
    }

    @Override
    public List<Board> list() {
        SqlSession session = sessionFactory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);
        List<Board> list = mapper.selectAll();
        return list;
    }

    @Override
    public List<Board> search(String word) {
        SqlSession session = sessionFactory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);
        List<Board> list = mapper.selectByWord(word);
        return list;
    }

    @Override
    public Board detail(int boardNo) {
        SqlSession session = sessionFactory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);
        Board board =  mapper.selectById(boardNo);
        if(board == null) {
            throw new IllegalArgumentException();
        }
        return board;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(Board b) throws Exception{
        SqlSession session = sessionFactory.openSession();

        BoardMapper mapper = session.getMapper(BoardMapper.class);
        int rowCnt = mapper.update(b);
        if (rowCnt == 0) {
            throw new IllegalArgumentException();
        }
        log.info("b.boardTitle={}", b.getBoardTitle());
        if(b.getBoardTitle() == null || b.getBoardTitle().equals("")) {
            throw new Exception();
        }
    }

    @Override
    public void remove(int boardNo) {
        SqlSession session = sessionFactory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);
        mapper.delete(boardNo);
    }

    @Override
    public Page<Board> list(int currentPage, int pageSize, String word) {
        SqlSession session = sessionFactory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("word", word);
        map.put("pageSize", pageSize);
        map.put("offset", (currentPage - 1) * pageSize);
        List<Board> list = mapper.selectByPage(map);

        int totalCnt = mapper.count(word);

        return new Page(list, pageSize, currentPage, totalCnt);
    }
}
