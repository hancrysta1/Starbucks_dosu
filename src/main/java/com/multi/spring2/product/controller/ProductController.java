package com.multi.spring2.product.controller;

import com.multi.spring2.product.exception.FindException;
import com.multi.spring2.product.service.ProductService;
import com.multi.spring2.product.vo.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Controller
@RequestMapping("/product")
@CrossOrigin(origins = "*"  //"Access-Control-Allow-origin"
)
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<Product>> list()throws FindException {
        List<Product> list = productService.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{prodNo}")
    public ResponseEntity<Product> detail(@PathVariable String prodNo) throws FindException     {

            Product p = productService.detail(prodNo);
            return ResponseEntity.ok(p);

    }
}
