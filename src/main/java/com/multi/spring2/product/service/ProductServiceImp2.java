package com.multi.spring2.product.service;

import com.multi.spring2.board.domain.Board;
import com.multi.spring2.board.domain.Page;
import com.multi.spring2.board.mapper.BoardMapper;
import com.multi.spring2.product.domain.Product;
import com.multi.spring2.product.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("serviceimp2")
@Slf4j
public class ProductServiceImp2 implements ProductService {
    private SqlSessionFactory sessionFactory;

    @Autowired
    public ProductServiceImp2(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Product> getProductList() {
        SqlSession session = sessionFactory.openSession();
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        List<Product> list = mapper.selectByAll();
        if (list.size() <= 0) {
            throw new IllegalArgumentException();
        }
        return list;
    }


    @Override
    public Product getProductDetail(String prodNo) {
        SqlSession session = sessionFactory.openSession();
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        Product product =  mapper.selectByWord(prodNo);
        if(product == null) {
            throw new IllegalArgumentException();
        }
        return product;
    }


}
