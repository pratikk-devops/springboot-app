package com.pg.PGTools.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contact {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String fullName;
	private String email;
	private String mobileNo;
	private String message;
	
	
	
	public Long getId() {
		return id;
	}
	public String getFullName() {
		return fullName;
	}
	public String getEmail() {
		return email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public String getMessage() {
		return message;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", fullName=" + fullName + ", email=" + email + ", mobileNo=" + mobileNo
				+ ", message=" + message + "]";
	}
	public Contact(Long id, String fullName, String email, String mobileNo, String message) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.message = message;
	}
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
