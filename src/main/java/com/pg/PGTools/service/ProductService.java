package com.pg.PGTools.service;

import java.util.List;

import com.pg.PGTools.entity.Products;

public interface ProductService {

//	Save Product
	Products saveProduct(Products product);
	
//	Get All Product
	List<Products> getAllProducts();
	
//	Get Product Id
	Products getProductById(Long id);
	
//	Delete Product
	void deleteProductById(Long id);
	
}
