package com.zhakav.ecommerce.security;


import com.zhakav.ecommerce.security.filter.AuthenticationFilter;
import com.zhakav.ecommerce.security.filter.ExceptionHandlerFilter;
import com.zhakav.ecommerce.security.filter.JWTAuthorizationFilter;
import com.zhakav.ecommerce.security.manager.CustomAuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@AllArgsConstructor
public class SecurityConfig  {

    private CustomAuthenticationManager customAuthenticationManager;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationFilter authenticationFilter=new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate");

        http
               .csrf().disable()
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST,SecurityConstants.REGISTER_PATH).permitAll()
                .requestMatchers(HttpMethod.DELETE,"/user/all").permitAll()
                .requestMatchers(HttpMethod.GET,"/user/all").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilterAfter(authenticationFilter, ExceptionHandlerFilter.class)
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
    
}