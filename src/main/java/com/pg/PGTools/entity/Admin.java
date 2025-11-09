package com.pg.PGTools.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String adminName;
	private String adminEmail;
	private String adminPassword;
	
	
	public Long getId() {
		return id;
	}
	public String getAdminName() {
		return adminName;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", adminName=" + adminName + ", adminEmail=" + adminEmail + ", adminPassword="
				+ adminPassword + "]";
	}
	public Admin(Long id, String adminName, String adminEmail, String adminPassword) {
		super();
		this.id = id;
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
