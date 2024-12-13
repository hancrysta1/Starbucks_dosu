package com.multi.spring2.cart.controller;

import com.multi.spring2.cart.dto.CartDTO;
import com.multi.spring2.cart.exception.FindException;
import com.multi.spring2.product.service.ProductService;
import com.multi.spring2.product.vo.Product;
import com.multi.spring2.security.exception.AccessTokenException;
import com.multi.spring2.security.util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class CartController {
    private ProductService productService;
    private JWTUtil jwtUtil;
    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/cart")
//    @CrossOrigin(
//            origins = "http://localhost:5173",
//            allowCredentials = "true",
//            methods={RequestMethod.GET,RequestMethod.POST,  RequestMethod.OPTIONS}
//            ,
//
//            allowedHeaders = {
//                    "Content-Type"
//                    , "Authorization"
//            }
//    )
    public ResponseEntity add(String prodNo, int quantity, HttpSession session) {
        log.info("cart add() session={}", session);
        Map<String, Integer> cart =(Map)session.getAttribute("cart");
        if(cart == null){
            cart = new HashMap<String, Integer>();
            session.setAttribute("cart", cart);
        }
        Integer oldQuantity = cart.get(prodNo);
        if(oldQuantity != null){
            quantity+=oldQuantity;
        }
        cart.put(prodNo, quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cart")
//    @CrossOrigin(
//            origins = "http://localhost:5173",
//            allowCredentials = "true"
//    )
    public ResponseEntity<List<CartDTO>> list(HttpSession session) throws FindException {
        log.info("cart list() session={}", session);

        Map<String, Integer> cart =(Map)session.getAttribute("cart");

        List<CartDTO> responseCart = new ArrayList<>();
        if(cart == null){
           throw new FindException();
        }

        for(Map.Entry<String, Integer> e: cart.entrySet()){
            String prodNo = e.getKey();
            Integer quantity = e.getValue();
            try {
                Product p = productService.detail(prodNo);
                CartDTO dto = new CartDTO(p.getProdNo(), p.getProdName(), p.getProdPrice(), quantity);
                responseCart.add(dto);
            } catch (com.multi.spring2.product.exception.FindException ex) {
                ex.printStackTrace();
                throw new FindException();
            }
        }
        return ResponseEntity.ok().body(responseCart);
    }
}
