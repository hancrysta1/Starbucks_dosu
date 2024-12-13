package com.multi.spring2.customer.service;

import com.multi.spring2.customer.exception.AddException;
import com.multi.spring2.customer.exception.FindException;
import com.multi.spring2.customer.mapper.CustomerMapper;
import com.multi.spring2.customer.vo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {
//    private SqlSessionTemplate sqlSessionTemplate;
    private CustomerMapper customerMapper;
    @Autowired
    public CustomerServiceImp(CustomerMapper customerMapper){
        this.customerMapper = customerMapper;
    }

    /**
     * 고객을 가입한다
     * @param customer
     */
    public void signup(Customer customer) throws AddException {
        try {
            customerMapper.insert(customer);
        }catch (Exception e){
            e.printStackTrace();
            throw new AddException();
        }
    }

    public void login(String id, String pwd) throws FindException{
        try {
            Customer c = customerMapper.findById(id);
            if(!c.getPwd().equals(pwd)){
                throw new FindException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FindException();
        }
    }

    public Customer showMyInfo(String id) throws FindException{
        try {
            Customer c = customerMapper.findById(id);
            if(c == null){
                throw new FindException();
            }
            return c;
        }catch (Exception e){
            e.printStackTrace();
            throw new FindException();
        }
    }
    public void modify(Customer customer) {}

    public void drop(Customer customer) {}
}
