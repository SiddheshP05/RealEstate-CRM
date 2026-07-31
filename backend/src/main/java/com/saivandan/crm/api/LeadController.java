package com.saivandan.crm.api;

import com.saivandan.crm.domain.*; import com.saivandan.crm.repo.*; import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;

@RestController @RequestMapping("/api/leads")
public class LeadController {
  private final LeadRepository leads;
  public LeadController(LeadRepository leads){this.leads=leads;}
  @GetMapping public List<Lead> list(){return leads.findAllByOrderByEnquiryDateDesc();}
  @PostMapping public ResponseEntity<Lead> create(@Valid @RequestBody LeadRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(leads.save(new Lead(r.customerName(),r.mobile(),r.email(),r.city(),r.budget(),r.configuration(),r.leadSource(),r.salesExecutive())));}
  @PatchMapping("/{id}/status") public Lead status(@PathVariable Long id,@RequestBody StatusRequest r){Lead l=leads.findById(id).orElseThrow();l.setStatus(LeadStatus.valueOf(r.status()));return leads.save(l);}
  public record LeadRequest(@NotBlank String customerName,@NotBlank String mobile,String email,String city,Integer budget,String configuration,String leadSource,String salesExecutive){}
  public record StatusRequest(@NotBlank String status){}
}
