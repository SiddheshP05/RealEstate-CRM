package com.saivandan.crm.domain;

import jakarta.persistence.*;

@Entity @Table(name="property_units")
public class PropertyUnit {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, unique=true) private String unitNumber;
  private String projectName; private String wing; private Integer floor; private String configuration; private Integer carpetArea; private Integer builtUpArea; private Integer price; private boolean parking; private String amenities;
  @Enumerated(EnumType.STRING) private UnitStatus status = UnitStatus.AVAILABLE;
  protected PropertyUnit() {}
  public PropertyUnit(String unitNumber,String projectName,String wing,Integer floor,String configuration,Integer carpetArea,Integer builtUpArea,Integer price,boolean parking,String amenities){this.unitNumber=unitNumber;this.projectName=projectName;this.wing=wing;this.floor=floor;this.configuration=configuration;this.carpetArea=carpetArea;this.builtUpArea=builtUpArea;this.price=price;this.parking=parking;this.amenities=amenities;}
  public Long getId(){return id;} public String getUnitNumber(){return unitNumber;} public String getProjectName(){return projectName;} public String getWing(){return wing;} public Integer getFloor(){return floor;} public String getConfiguration(){return configuration;} public Integer getCarpetArea(){return carpetArea;} public Integer getBuiltUpArea(){return builtUpArea;} public Integer getPrice(){return price;} public boolean isParking(){return parking;} public String getAmenities(){return amenities;} public UnitStatus getStatus(){return status;} public void setStatus(UnitStatus status){this.status=status;}
}
