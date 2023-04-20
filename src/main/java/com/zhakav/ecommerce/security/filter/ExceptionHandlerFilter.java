package com.zhakav.ecommerce.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zhakav.ecommerce.exeption.EntityNotFoundException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        try {

            filterChain.doFilter(request,response);

        } catch (EntityNotFoundException ex){

            editResponse(response,HttpServletResponse.SC_NOT_FOUND,"USERNAME DOES NOT EXIST!!");

        }catch (JWTVerificationException ex){

            editResponse(response,HttpServletResponse.SC_FORBIDDEN,"INVALID JWT TOKEN!!");

        } catch (RuntimeException ex){

            editResponse(response,HttpServletResponse.SC_BAD_REQUEST,"BAD REQUEST!!");

        }
    }

    private HttpServletResponse editResponse(
            @NonNull HttpServletResponse response,
            int status,
            String message)
            throws IOException {

        response.setStatus(status);
        response.getWriter().write(message);
        response.getWriter().flush();

        return response;
    }
}
