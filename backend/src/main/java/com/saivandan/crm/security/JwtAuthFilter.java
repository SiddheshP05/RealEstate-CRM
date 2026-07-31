package com.saivandan.crm.security;

import io.jsonwebtoken.JwtException; import jakarta.servlet.*; import jakarta.servlet.http.*; import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.authority.SimpleGrantedAuthority; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.stereotype.Component; import org.springframework.web.filter.OncePerRequestFilter; import java.io.IOException; import java.util.*;

@Component public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtService jwt; public JwtAuthFilter(JwtService jwt){this.jwt=jwt;}
  @Override protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain)throws ServletException,IOException{String header=request.getHeader("Authorization");if(header!=null&&header.startsWith("Bearer ")){try{String token=header.substring(7);String username=jwt.username(token);String role=jwt.role(token);var auth=new UsernamePasswordAuthenticationToken(username,null,List.of(new SimpleGrantedAuthority("ROLE_"+role)));SecurityContextHolder.getContext().setAuthentication(auth);}catch(JwtException ignored){}}chain.doFilter(request,response);}
}
