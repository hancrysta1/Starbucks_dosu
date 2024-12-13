package com.multi.spring2.order.service;

import com.multi.spring2.order.exception.AddException;
import com.multi.spring2.order.vo.OrderInfo;
import com.multi.spring2.order.vo.OrderLine;
import com.multi.spring2.product.vo.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.multi.spring2.config.AppConfig.class)
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    @Transactional
    public void addTest() throws AddException {
        String loginedId = "id1";
        OrderInfo info = new OrderInfo();
        info.setOrderId(loginedId);
        List<OrderLine> lines = new ArrayList<>();
        OrderLine line1 = new OrderLine();
        Product p1 = new Product(); p1.setProdNo("C0001");
        line1.setOrderP(p1);
        line1.setOrderQuantity(1);
        lines.add(line1);

        OrderLine line3 = new OrderLine();
        Product p3 = new Product(); p3.setProdNo("C0003");
        line3.setOrderP(p3);
        line3.setOrderQuantity(3);
        lines.add(line3);
        info.setLines(lines);
        orderService.add(info);
    }
}
