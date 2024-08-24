package com.multi.spring2.product.mapper;

import com.multi.spring2.product.domain.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> selectByAll();
    Product selectByWord(String word);
}
