package com.multi.spring2.product.mapper;
import com.multi.spring2.product.vo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    public void add(Product product);
    public Product findById(String prodNo);
    public List<Product> findByName(String word);
    public List<Product> findAll();
}
