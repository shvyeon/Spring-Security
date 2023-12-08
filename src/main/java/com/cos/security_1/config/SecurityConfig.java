package com.cos.security_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig{

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          http.csrf(c-> c.disable());
          http.authorizeRequests(authorizeRequest -> {

                    authorizeRequest
                            .requestMatchers(HttpMethod.GET, "/user/**").authenticated()
                            .requestMatchers(HttpMethod.GET, "/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                            .requestMatchers(HttpMethod.GET, "/admin/**").access("hasRole('ROLE_ADMIN')")
                            .anyRequest().permitAll(); // 모든 권한 허용
                    });
                    http.formLogin(f->f.loginPage("/loginForm").loginProcessingUrl("/login").defaultSuccessUrl("/"));
    //                                .formLogin()
    //                                .loginPage("/loginForm")
    //                                .loginProcessingUrl("/login") // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
    //                                .defaultSuccessUrl("/");
                return http.build();
    //
    }

}




