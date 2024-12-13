package com.multi.spring2.security.config;

import com.multi.spring2.security.filter.MyLoginFilter;
import com.multi.spring2.security.filter.MyRefreshTokenFilter;
import com.multi.spring2.security.filter.MyTokenCheckFilter;
import com.multi.spring2.security.handler.MyLoginFailureHandler;
import com.multi.spring2.security.handler.MyLoginSuccessHandler;
import com.multi.spring2.security.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.swing.*;
import java.util.List;

@Configuration
@EnableWebSecurity
@Slf4j
@ComponentScan(basePackages = {
        "com.multi.spring2.security.service",
        "com.multi.spring2.security.util"})
@MapperScan(basePackages = {"com.multi.spring2.security.mapper"}, annotationClass = org.apache.ibatis.annotations.Mapper.class)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance(); //비밀번호 암호와 안하기
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationManager();
        log.info("------authenticationManager = {}", authenticationManager);

        return http
                .cors() // CORS 설정 활성화
                .and()
                // 커스터마이즈한 필터를 등록

                //1.로그인인증 필터
                .addFilterBefore(
                        loginFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)

                //2. 인증유효성검사 필터
                .addFilterBefore(
                        tokenCheckFilter(userDetailsService),
                        UsernamePasswordAuthenticationFilter.class)

                //3. 리프레시토큰
                .addFilterBefore(
                        refreshTokenFilter(),
                        MyTokenCheckFilter.class)


                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()

                .and()

                .httpBasic().disable()        // 기본 HTTP 인증 비활성화
                .csrf().disable()       // CSRF 비활성화
                .formLogin().disable()  // formLogin 비활성화  관련 필터 해제
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 생성 모드 설정

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .build();
    }

    private MyRefreshTokenFilter refreshTokenFilter() {
        return new MyRefreshTokenFilter("/refreshToken", jwtUtil);
    }

    private MyTokenCheckFilter tokenCheckFilter(UserDetailsService userDetailsService) {
        return new MyTokenCheckFilter(userDetailsService, jwtUtil);
    }

    private MyLoginFilter loginFilter(AuthenticationManager authenticationManager) throws Exception {
        MyLoginSuccessHandler loginSuccessHandler = new MyLoginSuccessHandler(jwtUtil);
        MyLoginFailureHandler loginFailureHandler = new MyLoginFailureHandler();
        MyLoginFilter loginFilter = new MyLoginFilter(authenticationManager,
                "/login"
        );
        loginFilter.setAuthenticationSuccessHandler(loginSuccessHandler);
        loginFilter.setAuthenticationFailureHandler(loginFailureHandler);
        return loginFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authenticationProvider);
    }
}