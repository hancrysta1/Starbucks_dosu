package com.multi.spring2.security.vo;

import com.multi.spring2.customer.vo.Customer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Setter
@Getter

public class MyUser extends User {
    private Customer customer;
    public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    public MyUser(Customer customer){
        super(customer.getId(), customer.getPwd(), customer.getAuths());
        this.customer = customer;
    }
}
