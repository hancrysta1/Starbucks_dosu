package com.multi.spring2.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multi.spring2.security.handler.MyLoginFailureHandler;
import com.multi.spring2.security.handler.MyLoginSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j

public class MyLoginFilter
        extends
        UsernamePasswordAuthenticationFilter
//        AbstractAuthenticationProcessingFilter
{


    public MyLoginFilter(
            AuthenticationManager authenticationManager,
            String  filterProcessesUrl
            ) {

        super(authenticationManager);
        log.error("MyLoginFilter");
        setFilterProcessesUrl(filterProcessesUrl); // POST 로그인 요청 url
    }


    private Map<String, String> getJSonData(HttpServletRequest request) {

        log.info("--요청전달된 JSON 데이터를 분석해서 id, pw 전달 값을 Map으로 처리--");

        ObjectMapper objectMapper = new ObjectMapper();
        //JSON 데이터를 분석해서 id, pw 전달 값을 Map으로 처리
        Map<String, String> jsonData = new HashMap<>();
        try {
            Reader reader = new InputStreamReader(request.getInputStream());
            jsonData = objectMapper.readValue(reader, Map.class);
            log.info("jsonData:{}", jsonData);
            return jsonData;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("----MyLoginFilter  attemptAuthentication----");


        if (request.getMethod().equalsIgnoreCase("GET")) {
            log.info("GET METHOD NOT SUPPORT");
            return null;
        }
        log.info("request.getMethod()={}", request.getMethod());


//        log.info("--JSON 데이터로 UsernamePasswordAuthenticationToken생성--");
//        Map<String, String> jsonData = getJSonData(request);
//        UsernamePasswordAuthenticationToken authenticationToken
//                = new UsernamePasswordAuthenticationToken(
//                jsonData.get("id"),
//                jsonData.get("pwd"));

        String username = request.getParameter("id");
        String password = request.getParameter("pwd");

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);

        log.info("--AuthenticationManager에게 인증 요청 getAuthenticationManager()={}, id={}, pwd={}--", getAuthenticationManager(), username, password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }
}