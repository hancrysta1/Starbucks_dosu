package com.multi.spring2.product.service;

import com.multi.spring2.board.domain.Board;
import com.multi.spring2.product.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProductList();
    public Product getProductDetail(String prodNo);
}
