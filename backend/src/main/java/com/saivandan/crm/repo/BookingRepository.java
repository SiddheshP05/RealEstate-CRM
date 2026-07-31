package com.saivandan.crm.repo;
import com.saivandan.crm.domain.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface BookingRepository extends JpaRepository<Booking,Long> { List<Booking> findAllByOrderByBookingDateDesc(); }
