package com.pg.PGTools.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pg.PGTools.entity.Products;


public interface ProductRepository extends JpaRepository<Products, Long> {

}
