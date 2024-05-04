package com.codigo.mslogin.config;

import com.codigo.mslogin.service.JWTService;
import com.codigo.mslogin.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UsuarioService usuarioService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String autHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if(StringUtils.isEmpty(autHeader) || !StringUtils.startsWithIgnoreCase(autHeader, "Bearer ") ){
            filterChain.doFilter(request,response);
            return;
        }

        jwt = autHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        if(Objects.nonNull(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = usuarioService.userDetailService().loadUserByUsername(userEmail);
            if(jwtService.validateToken(jwt,userDetails)){

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authenticationToken  = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                securityContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(securityContext);
            }

        }
        filterChain.doFilter(request,response);

    }
}
