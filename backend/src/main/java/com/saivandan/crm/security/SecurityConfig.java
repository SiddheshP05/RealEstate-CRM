package com.saivandan.crm.security;

import org.springframework.context.annotation.*; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.config.http.SessionCreationPolicy; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.SecurityFilterChain; import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration @org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity public class SecurityConfig {
  @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
  @Bean SecurityFilterChain securityFilterChain(HttpSecurity http,JwtAuthFilter jwt) throws Exception {return http.csrf(c->c.disable()).cors(c->{}).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(a->a.requestMatchers("/api/auth/**","/api/health","/error").permitAll().requestMatchers("/api/leads/**","/api/follow-ups/**","/api/bookings/**").hasAnyRole("ADMIN","SALES_MANAGER","SALES_EXECUTIVE","MANAGEMENT").requestMatchers("/api/units/**").hasAnyRole("ADMIN","SALES_MANAGER","SALES_EXECUTIVE","SITE_MANAGER","MANAGEMENT").requestMatchers("/api/records/**").hasAnyRole("ADMIN","SALES_MANAGER","SALES_EXECUTIVE","ACCOUNTS","HR_MANAGER","PURCHASE_MANAGER","SITE_MANAGER","MANAGEMENT").anyRequest().authenticated()).addFilterBefore(jwt,UsernamePasswordAuthenticationFilter.class).build();}
}
