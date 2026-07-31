package com.saivandan.crm.api;

import com.saivandan.crm.domain.AppUser; import com.saivandan.crm.repo.AppUserRepository; import com.saivandan.crm.security.JwtService; import jakarta.validation.Valid; import jakarta.validation.constraints.NotBlank; import org.springframework.http.*; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/auth") public class AuthController {
  private final AppUserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
  public AuthController(AppUserRepository users,PasswordEncoder encoder,JwtService jwt){this.users=users;this.encoder=encoder;this.jwt=jwt;}
  @PostMapping("/login") public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){return users.findByUsername(request.username()).filter(AppUser::isActive).filter(u->encoder.matches(request.password(),u.getPasswordHash())).<ResponseEntity<?>>map(u->ResponseEntity.ok(new LoginResponse(jwt.create(u.getUsername(),u.getRole()),u.getUsername(),u.getDisplayName(),u.getRole()))).orElseGet(()->ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Invalid username or password")));}
  @GetMapping("/me") public UserResponse me(@org.springframework.security.core.annotation.AuthenticationPrincipal String username){AppUser u=users.findByUsername(username).orElseThrow();return new UserResponse(u.getUsername(),u.getDisplayName(),u.getRole());}
  public record LoginRequest(@NotBlank String username,@NotBlank String password){} public record LoginResponse(String token,String username,String displayName,String role){} public record UserResponse(String username,String displayName,String role){} public record ErrorResponse(String message){}
}
