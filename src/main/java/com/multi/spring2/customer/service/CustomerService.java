package com.multi.spring2.customer.service;

import com.multi.spring2.customer.exception.AddException;
import com.multi.spring2.customer.exception.FindException;
import com.multi.spring2.customer.mapper.CustomerMapper;
import com.multi.spring2.customer.vo.Customer;

public interface CustomerService {
    /**
     * 고객을 가입한다
     * @param customer
     */
    public void signup(Customer customer) throws AddException;

    /**
     * 로그인 한다
     * @param id
     * @param pwd
     */
    public void login(String id, String pwd) throws FindException;
    /**
     * 내정보를 조회한다
     * @param id
     * @return 고객
     */
    public Customer showMyInfo(String id) throws FindException;
    /**
     * 고객정보를 수정한다
     * @param customer
     */
    public void modify(Customer customer);

    /**
     * 고객을 탈퇴한다
     * @param customer
     */
    public void drop(Customer customer);
}
