package com.zhakav.ecommerce.security;


import com.zhakav.ecommerce.security.filter.AdminAuthenticationFilter;
import com.zhakav.ecommerce.security.filter.UserAuthenticationFilter;
import com.zhakav.ecommerce.security.filter.ExceptionHandlerFilter;
import com.zhakav.ecommerce.security.filter.JWTAuthorizationFilter;
import com.zhakav.ecommerce.security.manager.CustomAuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true , securedEnabled = true)
public class SecurityConfig  {

    private CustomAuthenticationManager customAuthenticationManager;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        UserAuthenticationFilter userAuthenticationFilter =new UserAuthenticationFilter(customAuthenticationManager);
        userAuthenticationFilter.setFilterProcessesUrl("/user/authenticate");

        AdminAuthenticationFilter adminAuthenticationFilter=new AdminAuthenticationFilter(customAuthenticationManager);
        adminAuthenticationFilter.setFilterProcessesUrl("/admin/authenticate");

        http
               .csrf().disable()
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST,SecurityConstants.USER_REGISTER_PATH).permitAll()
                .requestMatchers(HttpMethod.POST,SecurityConstants.ADMIN_REGISTER_PATH).permitAll()
                //.requestMatchers("/admin/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new ExceptionHandlerFilter(), UserAuthenticationFilter.class)
                .addFilterAfter(userAuthenticationFilter, ExceptionHandlerFilter.class)
                .addFilterAfter(adminAuthenticationFilter, UserAuthenticationFilter.class)
                .addFilterAfter(new JWTAuthorizationFilter(), AdminAuthenticationFilter.class )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
    
}