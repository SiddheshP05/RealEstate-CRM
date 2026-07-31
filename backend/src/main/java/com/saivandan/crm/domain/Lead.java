package com.saivandan.crm.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = "leads")
public class Lead {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  @Column(nullable=false) private String customerName;
  @Column(nullable=false) private String mobile;
  private String email;
  private String city;
  private Integer budget;
  private String interestedProperty;
  private String configuration;
  private String leadSource;
  private String salesExecutive;
  @Enumerated(EnumType.STRING) private LeadStatus status = LeadStatus.NEW;
  private LocalDateTime enquiryDate = LocalDateTime.now();

  protected Lead() {}
  public Lead(String customerName, String mobile, String email, String city, Integer budget, String configuration, String leadSource, String salesExecutive) {
    this.customerName=customerName; this.mobile=mobile; this.email=email; this.city=city; this.budget=budget; this.configuration=configuration; this.leadSource=leadSource; this.salesExecutive=salesExecutive;
  }
  public Long getId(){return id;} public String getCustomerName(){return customerName;} public String getMobile(){return mobile;} public String getEmail(){return email;} public String getCity(){return city;} public Integer getBudget(){return budget;} public String getInterestedProperty(){return interestedProperty;} public String getConfiguration(){return configuration;} public String getLeadSource(){return leadSource;} public String getSalesExecutive(){return salesExecutive;} public LeadStatus getStatus(){return status;} public LocalDateTime getEnquiryDate(){return enquiryDate;}
  public void setStatus(LeadStatus status){this.status=status;} public void setInterestedProperty(String value){this.interestedProperty=value;}
}
