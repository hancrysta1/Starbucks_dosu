package com.multi.spring2.board.dao;

import com.multi.spring2.board.domain.Board;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Qualifier("mybatis")
@Slf4j
public class BoardRepositoryMybatis implements BoardRepository{
//    private SqlSessionTemplate   sqlSessionTemplate;
    private SqlSessionFactory sqlSessionFactory;

//    @Autowired
//    public BoardRepositoryMybatis(SqlSessionTemplate sqlSessionTemplate) {
//        this.sqlSessionTemplate = sqlSessionTemplate;
//    }

    @Autowired
    public BoardRepositoryMybatis(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void save(Board board) {
//        sqlSessionTemplate.insert("BoardMapper.insert", board);
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("BoardMapper.insert", board);
    }

    @Override
    public List<Board> findAll() {
//       List<Board> list = sqlSessionTemplate.selectList("BoardMapper.selectAll");
        SqlSession session = sqlSessionFactory.openSession();
        List<Board> list = session.selectList("BoardMapper.selectAll");

       log.info("게시글 목록 {}", list);
       return list;
    }

    @Override
    public Board findById(int boardNo) {
        SqlSession session = sqlSessionFactory.openSession();
        return session.selectOne("BoardMapper.selectById", boardNo);
    }

    @Override
    public List<Board> findByWord(String word) {
        SqlSession session = sqlSessionFactory.openSession();
        return session.selectList(
                "BoardMapper.selectByWord",
                "%"+word + "%");
    }

    @Override
    public void update(Board board) {

    }

    @Override
    public void delete(int boardNo) {

    }
}
