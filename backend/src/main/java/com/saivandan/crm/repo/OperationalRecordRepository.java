package com.saivandan.crm.repo;
import com.saivandan.crm.domain.OperationalRecord; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface OperationalRecordRepository extends JpaRepository<OperationalRecord,Long> { List<OperationalRecord> findByModuleOrderByRecordDateDesc(String module); List<OperationalRecord> findAllByOrderByRecordDateDesc(); }
