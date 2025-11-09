package com.pg.PGTools.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Products {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String productName;
	private String productImage;
	
	
	public Long getId() {
		return id;
	}
	public String getProductName() {
		return productName;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	@Override
	public String toString() {
		return "Products [id=" + id + ", productName=" + productName + ", productImage=" + productImage + "]";
	}
	public Products(Long id, String productName, String productImage) {
		super();
		this.id = id;
		this.productName = productName;
		this.productImage = productImage;
	}
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
