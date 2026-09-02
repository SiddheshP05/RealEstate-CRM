package com.saivandan.crm.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity @Table(name="operational_records")
public class OperationalRecord {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false) private String module;
  @Column(nullable=false) private String reference;
  @Column(nullable=false) private String subject;
  private String detail; private String amount; private String status; private LocalDate recordDate = LocalDate.now(); private String owner;
  protected OperationalRecord() {}
  public OperationalRecord(String module,String reference,String subject,String detail,String amount,String status,String owner){this.module=module;this.reference=reference;this.subject=subject;this.detail=detail;this.amount=amount;this.status=status;this.owner=owner;}
  public Long getId(){return id;} public String getModule(){return module;} public String getReference(){return reference;} public String getSubject(){return subject;} public String getDetail(){return detail;} public String getAmount(){return amount;} public String getStatus(){return status;} public LocalDate getRecordDate(){return recordDate;} public String getOwner(){return owner;}
  public void setStatus(String status){this.status=status;} public void setAmount(String amount){this.amount=amount;} public void setDetail(String detail){this.detail=detail;} public void setOwner(String owner){this.owner=owner;}
}
