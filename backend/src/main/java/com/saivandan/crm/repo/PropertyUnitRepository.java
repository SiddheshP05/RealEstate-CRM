package com.saivandan.crm.repo;
import com.saivandan.crm.domain.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface PropertyUnitRepository extends JpaRepository<PropertyUnit,Long> { List<PropertyUnit> findAllByOrderByUnitNumberAsc(); long countByStatus(UnitStatus status); }
