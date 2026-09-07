package com.saivandan.crm.config;

import com.saivandan.crm.domain.*;
import com.saivandan.crm.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Configuration
public class SeedData {
  @Bean
  CommandLineRunner seed(LeadRepository leads, PropertyUnitRepository units,
                         AppUserRepository users, PasswordEncoder encoder,
                         OperationalRecordRepository records, JdbcTemplate jdbc) {
    return args -> {
      List<AppUser> defaults = List.of(
          new AppUser("admin", encoder.encode("admin123"), "System Administrator", "ADMIN"),
          new AppUser("superadmin", encoder.encode("superadmin123"), "Super Administrator", "SUPER_ADMIN"),
          new AppUser("priya", encoder.encode("priya123"), "Priya Deshmukh", "SALES_MANAGER"),
          new AppUser("sales", encoder.encode("sales123"), "Rahul Patil", "SALES_EXECUTIVE"),
          new AppUser("accounts", encoder.encode("accounts123"), "Neha Kulkarni", "ACCOUNTS"),
          new AppUser("finance", encoder.encode("finance123"), "Neha Kulkarni", "FINANCE"),
          new AppUser("hr", encoder.encode("hr123"), "Anita More", "HR_MANAGER"),
          new AppUser("hruser", encoder.encode("hruser123"), "Anita More", "HR"),
          new AppUser("purchase", encoder.encode("purchase123"), "Vikram Jadhav", "PURCHASE_MANAGER"),
          new AppUser("vendor", encoder.encode("vendor123"), "Pune Cement Supply", "VENDOR"),
          new AppUser("support", encoder.encode("support123"), "Customer Support", "SUPPORT"),
          new AppUser("site", encoder.encode("site123"), "Suresh Pawar", "SITE_MANAGER"),
          new AppUser("management", encoder.encode("management123"), "Snehal Kulkarni", "MANAGEMENT")
      );
      defaults.forEach(user -> users.findByUsername(user.getUsername()).map(existing -> {
        existing.setPasswordHash(user.getPasswordHash()); existing.setDisplayName(user.getDisplayName());
        existing.setRole(user.getRole()); existing.setActive(true); return users.save(existing);
      }).orElseGet(() -> users.save(user)));
      defaults.stream().map(AppUser::getRole).distinct().forEach(role -> {
        jdbc.update("INSERT INTO roles(role_name) VALUES (?) ON CONFLICT (role_name) DO NOTHING", role);
        AppUser user = defaults.stream().filter(u -> u.getRole().equals(role)).findFirst().orElseThrow();
        Long roleId = jdbc.queryForObject("SELECT id FROM roles WHERE role_name = ?", Long.class, role);
        Long userId = users.findByUsername(user.getUsername()).orElseThrow().getId();
        jdbc.update("INSERT INTO user_roles(user_id, role_id) VALUES (?, ?) ON CONFLICT DO NOTHING", userId, roleId);
      });
      if (leads.count() == 0) leads.saveAll(List.of(
          new Lead("Aarav Shah", "9876543210", "aarav@example.com", "Pune", 6500000, "2 BHK", "Website", "Priya Deshmukh"),
          new Lead("Meera Joshi", "9822001122", "meera@example.com", "Pune", 9000000, "3 BHK", "Referral", "Rahul Patil")));
      if (units.count() == 0) units.saveAll(List.of(
          new PropertyUnit("A-101", "Sai Vandan Complex", "A", 1, "1 BHK", 520, 680, 4200000, true, "Garden, Security"),
          new PropertyUnit("A-202", "Sai Vandan Complex", "A", 2, "2 BHK", 780, 980, 6400000, true, "Clubhouse, Lift"),
          new PropertyUnit("B-301", "Sai Vandan Complex", "B", 3, "3 BHK", 1120, 1400, 8900000, true, "Pool, Gym"),
          new PropertyUnit("B-402", "Sai Vandan Complex", "B", 4, "4 BHK", 1560, 1900, 12500000, true, "Terrace, Parking")));
      if (records.count() == 0) records.saveAll(List.of(
          new OperationalRecord("Customer Payments", "PAY-2026-001", "Meera Joshi", "A-202 · Agreement payment", "₹6.4L", "Pending", "Neha Kulkarni"),
          new OperationalRecord("Vendor Bills", "VE-104", "Shree Electricals", "Electrical work · Sai Vandan Complex", "₹2.1L", "Pending", "Neha Kulkarni"),
          new OperationalRecord("Projects", "PROJECT-001", "Sai Vandan Complex", "Residential flats · 76% progress", "", "On schedule", "Suresh Pawar"),
          new OperationalRecord("Site Visits", "VIS-2026-031", "Aarav Shah", "Unit A-202 · Today 10:30 AM", "", "Confirmed", "Suresh Pawar"),
          new OperationalRecord("Finance", "KPI-FIN-07", "Collections", "Current period", "₹38.4L", "92% of target", "Snehal Kulkarni")));
      if (jdbc.queryForObject("SELECT COUNT(*) FROM operational_records WHERE module = 'Attendance'", Long.class) == 0)
        records.saveAll(List.of(
          new OperationalRecord("Attendance", "ATT-DEMO-001", "Priya Deshmukh", "09:42 AM", "", "Present", "HR"),
          new OperationalRecord("Attendance", "ATT-DEMO-002", "Rahul Patil", "09:51 AM", "", "Present", "HR")));
    };
  }
}
