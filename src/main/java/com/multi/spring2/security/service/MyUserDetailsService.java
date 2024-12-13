package com.multi.spring2.security.service;

import com.multi.spring2.customer.vo.Customer;
import com.multi.spring2.security.mapper.UserDetailsMapper;
import com.multi.spring2.security.vo.MyUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    final private UserDetailsMapper mapper;
//    public MyUserDetailsService(UserDetailsMapper mapper) {
//        this.mapper = mapper;
//    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Customer vo = mapper.get(id);
        if(vo == null) {
            throw new UsernameNotFoundException(id + "은 없는 id입니다.");
        }
        log.info("loadUserByUsername vo={}", vo);
        return new MyUser(vo);
    }

}
