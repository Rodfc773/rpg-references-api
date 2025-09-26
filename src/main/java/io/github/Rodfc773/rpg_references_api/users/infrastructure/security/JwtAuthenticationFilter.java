package io.github.Rodfc773.rpg_references_api.users.infrastructure.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    public JwtAuthenticationFilter(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        String header = request.getHeader("Authorization");


        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        try{

            var token = this.tokenService.validateToken(header);

            if (token == null){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            var roles = token.getClaim("roles").asList(String.class);

            if (roles == null) {
                roles = Collections.emptyList();
            }

            var grants = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .toList();

            request.setAttribute("user_id", token.getSubject());

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);

            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendError(401, e.getMessage());
        }
    }
}
