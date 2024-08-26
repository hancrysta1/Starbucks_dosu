package com.multi.spring2.product.controller;

import com.multi.spring2.product.domain.Product;
import com.multi.spring2.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //@Controller + @ResponseBody
@RequestMapping("/product")
@Slf4j
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("serviceimp2") ProductService productService) {
        this.productService = productService;
    }

    public ProductController() {
        System.out.println("BoardController created");
    }


    @GetMapping({"/{prodNo}"})
    public ResponseEntity<Product> productDetail (
            @PathVariable(value = "prodNo", required = false) Optional<String> prodNo) throws Exception{
        String word = prodNo.orElse("");
        Product product = productService.getProductDetail(word);
        return ResponseEntity.ok(product);
    }


    @GetMapping({"/list"})
    public ResponseEntity<List<Product>> productList() throws Exception{
        List<Product> list = productService.getProductList();
        return ResponseEntity.ok(list);
    }
}