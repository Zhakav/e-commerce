package com.zhakav.ecommerce.security.manager;

import com.auth0.jwt.JWT;
import com.zhakav.ecommerce.entity.AdminUser;
import com.zhakav.ecommerce.entity.User;
import com.zhakav.ecommerce.service.AdminUserService;
import com.zhakav.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private UserService userService;
    private AdminUserService adminService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {



        if(!authentication.getAuthorities().isEmpty())
            return authenticateUser(authentication);

        else
            return authenticateAdmin(authentication);

    }

    private UsernamePasswordAuthenticationToken authenticateUser(Authentication authentication){

        User user=userService.getByUsername(authentication.getName());

        if(!bCryptPasswordEncoder.matches(
                authentication.getCredentials().toString(),
                user.getPassword())){

            throw new BadCredentialsException("Wrong Password!!!");

        }

        return new UsernamePasswordAuthenticationToken(
                authentication.getName(),
                user.getPassword());

    }

    private UsernamePasswordAuthenticationToken authenticateAdmin(Authentication authentication){
        AdminUser admin=adminService.getByUsername(authentication.getName());

        if(!bCryptPasswordEncoder.matches(
                authentication.getCredentials().toString(),
                admin.getPassword())){

            throw new BadCredentialsException("Wrong Password!!!");

        }

        Collection<? extends GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(admin.getAdminType().getAdminType()));

        return new UsernamePasswordAuthenticationToken(
                authentication.getName(),
                admin.getPassword(),
                authorities);
    }
}
