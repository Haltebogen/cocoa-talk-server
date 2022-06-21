package com.haltebogen.gittalk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(
            HttpSecurity http
    ) throws Exception {
        http
                .csrf().disable() //csrf
                .exceptionHandling()// 예외처리
                .and()
                .authorizeRequests()
                .antMatchers("/oauth2/**").authenticated()
                .anyRequest().permitAll(); // 권한 설정
        return http.build();
    }



}
