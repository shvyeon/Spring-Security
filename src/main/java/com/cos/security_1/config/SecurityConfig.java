package com.cos.security_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨
public class SecurityConfig{

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests(authorizeRequest -> {
                    try {
                        authorizeRequest
                                        .requestMatchers(HttpMethod.GET, "/user/**").authenticated()
                                        .requestMatchers(HttpMethod.GET, "/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                                        .requestMatchers(HttpMethod.GET, "/admin/**").access("hasRole('ROLE_ADMIN')")
                                        .anyRequest().permitAll() // 모든 권한 허용
                                        .and()
                                        .formLogin()
                                        .loginPage("/loginForm");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });


        return http.build();
    }
}
