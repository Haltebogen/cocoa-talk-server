package com.haltebogen.gittalk.config;

import com.haltebogen.gittalk.oauth.BaseOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final BaseOauth2UserService baseOauth2UserService;
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
                .anyRequest().permitAll() // 권한 설정
                .and()
                .oauth2Login().loginPage("/github").userInfoEndpoint().userService(baseOauth2UserService);
        return http.build();
    }



}
