package com.pg.PGTools.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.pg.PGTools.entity.Customers;

public interface CustomerService {

	 Customers saveCustomer(MultipartFile file, String name, String location) throws IOException;
	 
	 List<Customers> getAllCustomers();
	 
	 Optional<Customers> getCustomerById(Long id);
	 
	 Customers updateCustomer(Long id, MultipartFile file, String name, String location) throws IOException;
	 
	 void deleteCustomerById(Long id);
}
	