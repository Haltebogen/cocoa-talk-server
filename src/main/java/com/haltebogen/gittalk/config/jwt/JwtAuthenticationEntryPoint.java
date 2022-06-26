package com.haltebogen.gittalk.config.jwt;

import com.haltebogen.gittalk.trace.Trace;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final String AUTHORIZATION_EXCEPTION_ERROR_MESSAGE = "unauthorized access token";
    @Trace
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, AUTHORIZATION_EXCEPTION_ERROR_MESSAGE);
    }
}
