package com.saivandan.crm.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity @Table(name="bookings")
public class Booking {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne(optional=false) private Lead lead;
  @ManyToOne(optional=false) private PropertyUnit unit;
  private Integer bookingAmount; private LocalDate bookingDate;
  @Enumerated(EnumType.STRING) private BookingStatus status = BookingStatus.CONFIRMED;
  protected Booking() {}
  public Booking(Lead lead,PropertyUnit unit,Integer bookingAmount){this.lead=lead;this.unit=unit;this.bookingAmount=bookingAmount;this.bookingDate=LocalDate.now();}
  public Long getId(){return id;} public Lead getLead(){return lead;} public PropertyUnit getUnit(){return unit;} public Integer getBookingAmount(){return bookingAmount;} public LocalDate getBookingDate(){return bookingDate;} public BookingStatus getStatus(){return status;}
}
