package com.multi.spring2.product.service;

import com.multi.spring2.product.exception.FindException;
import com.multi.spring2.product.mapper.ProductMapper;
import com.multi.spring2.product.vo.Product;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@Service

public class ProductServiceImp implements ProductService {
    private SqlSessionTemplate sqlSessionTemplate;

    //생성자가 1개이면 @Autowired생략가능
    public ProductServiceImp(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public List<Product> list() throws FindException{
        try {
            ProductMapper mapper = sqlSessionTemplate.getMapper(ProductMapper.class);
            List<Product> list = mapper.findAll();
            return list;
        }catch(Exception e){
            e.printStackTrace();
            throw new FindException();
        }
    }
    @Override
    public Product detail(String prodNo) throws FindException {
        ProductMapper mapper = sqlSessionTemplate.getMapper(ProductMapper.class);
        Product p = mapper.findById(prodNo);
        if(p == null){
            throw new FindException();
        }
        return p;
    }
}