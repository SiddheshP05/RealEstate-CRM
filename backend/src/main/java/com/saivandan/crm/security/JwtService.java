package com.saivandan.crm.security;

import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import javax.crypto.SecretKey; import java.nio.charset.StandardCharsets; import java.util.*;

@Service public class JwtService {
  private final SecretKey key; private final long expirationMs;
  public JwtService(@Value("${app.security.jwt-secret:change-this-development-secret-key-to-a-long-value}") String secret,@Value("${app.security.expiration-ms:86400000}") long expirationMs){key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));this.expirationMs=expirationMs;}
  public String create(String username,String role){Date now=new Date();return Jwts.builder().subject(username).claim("role",role).issuedAt(now).expiration(new Date(now.getTime()+expirationMs)).signWith(key).compact();}
  public String username(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();}
  public String role(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().get("role",String.class);}
}
