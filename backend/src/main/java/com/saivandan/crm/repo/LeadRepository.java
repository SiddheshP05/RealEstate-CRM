package com.saivandan.crm.repo;
import com.saivandan.crm.domain.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface LeadRepository extends JpaRepository<Lead,Long> { List<Lead> findAllByOrderByEnquiryDateDesc(); long countByStatus(LeadStatus status); }
