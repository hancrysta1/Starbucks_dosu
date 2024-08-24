package com.multi.spring2.product.dao;

import com.multi.spring2.product.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("mybatis")
@Slf4j
public class ProductRepositoryMybatis implements ProductRepository {
    private SqlSessionFactory sqlSessionFactory;


    @Autowired
    public ProductRepositoryMybatis(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }


    @Override
    public List<Product> findByAll() {
        SqlSession session = sqlSessionFactory.openSession();
        List<Product> list = session.selectList("ProductMapper.selectByAll");

       log.info("상품 전체 목록 {}", list);
       return list;
    }

    @Override
    public Product findByWord(String word) {
        SqlSession session = sqlSessionFactory.openSession();
        return session.selectOne(
                "ProductMapper.selectByWord", word);
    }
}
