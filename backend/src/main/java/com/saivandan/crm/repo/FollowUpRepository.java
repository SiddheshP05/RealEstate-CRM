package com.saivandan.crm.repo;
import com.saivandan.crm.domain.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface FollowUpRepository extends JpaRepository<FollowUp,Long> { List<FollowUp> findAllByOrderByScheduledAtAsc(); }
