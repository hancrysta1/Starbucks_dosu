package com.multi.spring2.product.domain;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@ToString
public class Product {
    private String prodNo;
    private String prodName;
    private String prodPrice;
}
