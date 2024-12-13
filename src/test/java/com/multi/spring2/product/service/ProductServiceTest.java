package com.multi.spring2.product.service;

import com.multi.spring2.product.exception.FindException;
import com.multi.spring2.product.vo.Product;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) //SpringFramework Junit실행이 가능하도록 설정한다
@ContextConfiguration(classes = com.multi.spring2.config.AppConfig.class)

public class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Test
    public void testList() throws FindException {
        List<Product> list = productService.list();

        assertTrue(list.size()> 0);
    }
    @Test
    public void testDetail() throws FindException {
        String prodNo = "C0001";
        String expectedName = "아메리카노";
        int expectedPrice = 1100;
        Product p  = productService.detail(prodNo);
        assertEquals(expectedName, p.getProdName());
        assertEquals(expectedPrice, p.getProdPrice());
    }

    /**
     * 상품상세 예외 발생 테스트
     */
    @Test
    public void testNotFoundDetail() {
        String prodNo = "없는상품번호";
        FindException expectedException;
        String expectedMessage = "상품이 없습니다";
        int expectedPrice = 1100;
        expectedException = assertThrows(FindException.class,
                ()->productService.detail(prodNo));
//        assertEquals(expectedMessage, expectedException.getMessage());
    }
}
