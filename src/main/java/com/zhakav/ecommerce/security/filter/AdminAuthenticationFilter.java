package com.zhakav.ecommerce.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhakav.ecommerce.entity.AdminUser;
import com.zhakav.ecommerce.entity.User;
import com.zhakav.ecommerce.security.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@RequiredArgsConstructor
public class AdminAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @lombok.NonNull
    private AuthenticationManager authenticationManager;

    String role=null;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException {

        try {

            AdminUser admin= new ObjectMapper().readValue(request.getInputStream(),AdminUser.class);
            Authentication authentication=new UsernamePasswordAuthenticationToken(admin.getUsername(),admin.getPassword());

            Authentication authenticationResult=authenticationManager.authenticate(authentication);

            role= String.valueOf(authenticationResult.getAuthorities().stream().toList().get(0));

            return authenticationResult;

        }catch (IOException ex){ throw new RuntimeException(); }

    }

    @Override
    protected void unsuccessfulAuthentication(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull AuthenticationException failed) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();

    }

    @Override
    protected void successfulAuthentication(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain chain,
            @NonNull Authentication authResult)
            throws IOException, ServletException {

        System.out.println(role);

        String token= JWT.create()
                .withSubject(authResult.getName())
                .withClaim("roles",role)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));

        response.addHeader(SecurityConstants.AUTHORIZATION,SecurityConstants.BEARER + token);

    }
}
