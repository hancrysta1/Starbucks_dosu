package com.multi.spring2.order.service;

import com.multi.spring2.order.exception.AddException;
import com.multi.spring2.order.exception.FindException;
import com.multi.spring2.order.mapper.OrderMapper;
import com.multi.spring2.order.vo.OrderInfo;
import com.multi.spring2.order.vo.OrderLine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class OrderServiceImp implements OrderService {
    private OrderMapper orderMapper;

    //생성자가 1개이면 @Autowired생략가능
    public OrderServiceImp(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional(rollbackFor = AddException.class)
    public void add(OrderInfo info) throws AddException {

        addInfo(orderMapper, info);
        for (OrderLine line : info.getLines()) {
            addLine(orderMapper, line);
        }
    }

    /**
     * 주문 기본정보 추가
     *
     * @param mapper
     * @param info
     * @throws AddException
     */
    private void addInfo(OrderMapper orderMapper, OrderInfo info) throws AddException {
        try {
            orderMapper.insertInfo(info);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 주문 상세정보 추가
     *
     * @param mapper
     * @param line
     * @throws AddException
     */
    private void addLine(OrderMapper orderMapper, OrderLine line) throws AddException {
        try {
            orderMapper.insertLine(line);
        } catch (Exception e) {
            throw new AddException(e.getMessage());
        }
    }

    @Override
    public List<OrderInfo> list(String orderId) throws FindException {
        List<OrderInfo> list;
        try {
            return orderMapper.findByOrderId(orderId);
        } catch (Exception e) {
            throw new FindException(e.getMessage());
        }
    }
}
