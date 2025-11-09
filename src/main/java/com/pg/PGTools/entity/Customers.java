package com.pg.PGTools.entity;

import jakarta.persistence.*;


@Entity
public class Customers {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String customerLogo;
	private String customerName;
	private String customerLocation;
	
	
	public Long getId() {
		return id;
	}
	public String getCustomerLogo() {
		return customerLogo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public String getCustomerLocation() {
		return customerLocation;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCustomerLogo(String customerLogo) {
		this.customerLogo = customerLogo;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}
	@Override
	public String toString() {
		return "Customers [id=" + id + ", customerLogo=" + customerLogo + ", customerName=" + customerName
				+ ", customerLocation=" + customerLocation + "]";
	}
	public Customers(Long id, String customerLogo, String customerName, String customerLocation) {
		super();
		this.id = id;
		this.customerLogo = customerLogo;
		this.customerName = customerName;
		this.customerLocation = customerLocation;
	}
	public Customers() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
