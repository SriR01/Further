package com.further.service;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setHeader("WWW-Authenticate", "Bearer realm=\"Access to protected resources\"");
        String message = (authException != null && authException.getMessage() != null)
                ? authException.getMessage()
                : "Unauthorized";
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);
    }
}
