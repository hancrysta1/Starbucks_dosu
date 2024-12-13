package com.multi.spring2.customer.vo;

import com.multi.spring2.security.vo.CustomerAuth;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter @ToString
public class Customer {
    private String id;
    private String pwd;
    private String name;

    //Security : Authorization(권한용) 추가
    private List<CustomerAuth>  auths;
}
