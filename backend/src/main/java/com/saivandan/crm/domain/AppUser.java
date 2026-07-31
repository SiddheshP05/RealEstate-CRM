package com.saivandan.crm.domain;

import jakarta.persistence.*;

@Entity @Table(name="app_users")
public class AppUser {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, unique=true) private String username;
  @Column(nullable=false) private String passwordHash;
  @Column(nullable=false) private String displayName;
  @Column(nullable=false) private String role;
  @Column(nullable=false) private boolean active = true;
  protected AppUser() {}
  public AppUser(String username,String passwordHash,String displayName,String role){this.username=username;this.passwordHash=passwordHash;this.displayName=displayName;this.role=role;}
  public Long getId(){return id;} public String getUsername(){return username;} public String getPasswordHash(){return passwordHash;} public String getDisplayName(){return displayName;} public String getRole(){return role;} public boolean isActive(){return active;}
}
