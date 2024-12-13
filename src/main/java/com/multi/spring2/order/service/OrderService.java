package com.multi.spring2.order.service;

import com.multi.spring2.order.exception.AddException;
import com.multi.spring2.order.exception.FindException;
import com.multi.spring2.order.vo.OrderInfo;

import java.util.List;

public interface OrderService {
    public void add(OrderInfo orderInfo) throws AddException;
    public List<OrderInfo> list(String orderId) throws FindException;
}
