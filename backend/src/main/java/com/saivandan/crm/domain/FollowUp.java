package com.saivandan.crm.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name="follow_ups")
public class FollowUp {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne(optional=false) private Lead lead;
  private String type; private LocalDateTime scheduledAt; private String remarks; private String executive; private boolean completed;
  protected FollowUp() {}
  public FollowUp(Lead lead,String type,LocalDateTime scheduledAt,String remarks,String executive){this.lead=lead;this.type=type;this.scheduledAt=scheduledAt;this.remarks=remarks;this.executive=executive;}
  public Long getId(){return id;} public Lead getLead(){return lead;} public String getType(){return type;} public LocalDateTime getScheduledAt(){return scheduledAt;} public String getRemarks(){return remarks;} public String getExecutive(){return executive;} public boolean isCompleted(){return completed;} public void setCompleted(boolean value){completed=value;}
}
