package com.algohire.backend.security.filter;


import com.algohire.backend.security.CustomUserDetails;
import com.algohire.backend.service.CustomUserDetailsService;
import com.algohire.backend.service.impl.JwtServiceImpal;
import com.algohire.backend.service.impl.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtServiceImpal jwtServiceImpal;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader= request.getHeader("Authorization");

        String username=null;
        String jwt =null;

        if(authHeader!=null && authHeader.startsWith("Bearer")){
            jwt=authHeader.substring(7);
            username=jwtServiceImpal.extractUsername(jwt);
        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            CustomUserDetails  customUserDetails= (CustomUserDetails) this.customUserDetailsService.loadUserByUsername(username);

            if(jwtServiceImpal.validateToken(jwt,customUserDetails)){
                UsernamePasswordAuthenticationToken token=
                        new UsernamePasswordAuthenticationToken(customUserDetails,null,customUserDetails.getAuthorities());

                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);

            }

        }
        filterChain.doFilter(request,response);

    }
}
