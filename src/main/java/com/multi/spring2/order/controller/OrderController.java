package com.multi.spring2.order.controller;


import com.multi.spring2.order.exception.AddException;
import com.multi.spring2.order.exception.FindException;
import com.multi.spring2.order.service.OrderService;
import com.multi.spring2.order.vo.OrderInfo;
import com.multi.spring2.order.vo.OrderLine;
import com.multi.spring2.product.vo.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Slf4j
@Controller
public class OrderController {
    private OrderService service;
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderInfo> add(HttpSession session, Authentication auth)  throws AddException{
        log.info("auth.getName()={}", auth.getName());
        log.info("auth.getPrincipal()={}", auth.getPrincipal());
        String loginedId = auth.getName();
        Map<String, Integer> cart = (Map) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        OrderInfo info = new OrderInfo();
        info.setOrderId(loginedId);
        List<OrderLine> lines = new ArrayList<>();
        for (Map.Entry<String, Integer> e : cart.entrySet()) {
            String prodNo = e.getKey();
            Integer quantity = e.getValue();
            OrderLine line = new OrderLine();
//            line.setOrderNo();
            Product p = new Product();
            p.setProdNo(prodNo);
            line.setOrderP(p);
            line.setOrderQuantity(quantity);
            lines.add(line);
        }
        info.setLines(lines);

        service.add(info);

        session.removeAttribute("cart");
        return ResponseEntity.ok().build();
    }
//    public ResponseEntity add(HttpSession session) throws AddException {
//        String loginedId = (String) session.getAttribute("loginedId");
//        if (loginedId == null) {
//           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//        Map<String, Integer> cart = (Map) session.getAttribute("cart");
//        if (cart == null || cart.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        OrderInfo info = new OrderInfo();
//        info.setOrderId(loginedId);
//        List<OrderLine> lines = new ArrayList<>();
//        for (Map.Entry<String, Integer> e : cart.entrySet()) {
//            String prodNo = e.getKey();
//            Integer quantity = e.getValue();
//            OrderLine line = new OrderLine();
////            line.setOrderNo();
//            Product p = new Product();
//            p.setProdNo(prodNo);
//            line.setOrderP(p);
//            line.setOrderQuantity(quantity);
//            lines.add(line);
//        }
//        info.setLines(lines);
//
//        log.info("in order doPost 3");
//        //////////////////////TODO
//        service.add(info);
//
//        log.info("in order doPost 4");
//        session.removeAttribute("cart");
//
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/order")
//    @CrossOrigin(
//            origins = "http://localhost:5173",
//            allowCredentials = "true"
//    )
    public ResponseEntity<List<OrderInfo>> list(Authentication authentication) throws FindException {

        String loginedId = authentication.getName();
//        MyUser principal =(MyUser)authentication.getPrincipal();
//        String loginedId = principal.getCustomer().getId();

        if(loginedId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); //로그인 안한경우
        }else {
            List<OrderInfo> orderInfoList=  service.list(loginedId);
            if(orderInfoList == null || orderInfoList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok(orderInfoList);
        }
    }
//    public ResponseEntity<List<OrderInfo>> list(HttpSession session) throws FindException {
//        String loginedId = (String) session.getAttribute("loginedId");
//        if(loginedId == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); //로그인 안한경우
//        }else {
//            List<OrderInfo> orderInfoList=  service.list(loginedId);
//            if(orderInfoList == null || orderInfoList.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            }
//            return ResponseEntity.ok(orderInfoList);
//        }
//    }
}
