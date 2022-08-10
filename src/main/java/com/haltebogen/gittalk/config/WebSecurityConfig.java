package com.haltebogen.gittalk.config;

import com.haltebogen.gittalk.config.jwt.JwtAuthenticationEntryPoint;
import com.haltebogen.gittalk.config.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtTokenFilter jwtTokenFilter;

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/auth/member",
            "/auth/login",
            "/github",
            "/api/auth/github/callback"
    };


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(
            HttpSecurity http
    ) throws Exception {
        return http
                .cors().and()
                .csrf().disable() //csrf
                .exceptionHandling().
                authenticationEntryPoint(jwtAuthenticationEntryPoint).and()// 예외처리
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(
                        jwtTokenFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/api/v1/**").authenticated()
                .anyRequest().authenticated().and()
                .build(); // 권한 설정
    }
}
