package com.pg.PGTools.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InductrieWork {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String name;
	private String image;
	
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getImage() {
		return image;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "InductrieWork [id=" + id + ", name=" + name + ", image=" + image + "]";
	}
	public InductrieWork(Long id, String name, String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}
	public InductrieWork() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
