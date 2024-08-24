package com.multi.spring2.product.dao;

import com.multi.spring2.product.domain.Product;

import java.util.List;

public interface ProductRepository {
    public List<Product> findByAll();
    public Product findByWord(String prodNo);
}
