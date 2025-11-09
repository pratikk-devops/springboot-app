package com.pg.PGTools.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pg.PGTools.entity.Products;
import com.pg.PGTools.repository.ProductRepository;
import com.pg.PGTools.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
    private ProductRepository productRepository;
	
	@Override
    public Products saveProduct(Products product) {
        return productRepository.save(product);
    }
	
	@Override
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }
	
//	Get Product id for Edit
	@Override
	public Products getProductById(Long id) {
	    return productRepository.findById(id).orElse(null);
	}
	
	@Override
	public void deleteProductById(Long id) {
	    productRepository.deleteById(id);
	}
}
