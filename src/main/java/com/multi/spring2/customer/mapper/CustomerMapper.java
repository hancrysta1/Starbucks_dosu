package com.multi.spring2.customer.mapper;
import com.multi.spring2.customer.vo.Customer;
import com.multi.spring2.product.vo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    public void insert(Customer customer);
    public Customer findById(String id);
}