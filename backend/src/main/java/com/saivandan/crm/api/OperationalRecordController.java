package com.saivandan.crm.api;

import com.saivandan.crm.domain.OperationalRecord; import com.saivandan.crm.repo.OperationalRecordRepository; import jakarta.validation.Valid; import jakarta.validation.constraints.NotBlank; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;

@RestController @RequestMapping("/api/records") public class OperationalRecordController {
  private final OperationalRecordRepository records; public OperationalRecordController(OperationalRecordRepository records){this.records=records;}
  @GetMapping public List<OperationalRecord> list(@RequestParam(required=false) String module){return module==null?records.findAllByOrderByRecordDateDesc():records.findByModuleOrderByRecordDateDesc(module);}
  @PostMapping public ResponseEntity<OperationalRecord> create(@Valid @RequestBody Request r){return ResponseEntity.status(HttpStatus.CREATED).body(records.save(new OperationalRecord(r.module(),r.reference(),r.subject(),r.detail(),r.amount(),r.status(),r.owner())));}
  @PatchMapping("/{id}") public OperationalRecord update(@PathVariable Long id,@RequestBody Update r){OperationalRecord x=records.findById(id).orElseThrow();if(r.status()!=null)x.setStatus(r.status());if(r.amount()!=null)x.setAmount(r.amount());if(r.detail()!=null)x.setDetail(r.detail());if(r.owner()!=null)x.setOwner(r.owner());return records.save(x);}
  public record Request(@NotBlank String module,@NotBlank String reference,@NotBlank String subject,String detail,String amount,String status,String owner){} public record Update(String detail,String amount,String status,String owner){}
}
