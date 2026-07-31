package com.saivandan.crm.repo;
import com.saivandan.crm.domain.AppUser; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface AppUserRepository extends JpaRepository<AppUser,Long> { Optional<AppUser> findByUsername(String username); }
