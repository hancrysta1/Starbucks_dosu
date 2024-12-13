package com.multi.spring2.security.mapper;

import com.multi.spring2.customer.vo.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDetailsMapper {
    Customer get(String id);
}
