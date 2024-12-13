package com.multi.spring2.order.mapper;

import com.multi.spring2.order.vo.OrderInfo;
import com.multi.spring2.order.vo.OrderLine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.core.annotation.Order;

import java.util.List;
@Mapper
public interface OrderMapper {
    public void insertInfo(OrderInfo info);
    public void insertLine(OrderLine line);
    public List<OrderInfo> findByOrderId(String orderId);
}
