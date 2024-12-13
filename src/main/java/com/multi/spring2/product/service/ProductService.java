package com.multi.spring2.product.service;

import com.multi.spring2.product.exception.FindException;
import com.multi.spring2.product.vo.Product;

import java.util.List;
import java.util.NoSuchElementException;

public interface ProductService {
    /**
     * 상품전체목록을 반환합니다
     * @return 상품전체목록
     */
    public List<Product> list() throws FindException;

    /**
     * 상품을 반환합니다
     * @param prodNo 상품번호
     * @return 상품
     * @throws NoSuchElementException 상품이 없으면 예외가 발생합니다
     */
    public Product detail(String prodNo)  throws FindException;;
}
