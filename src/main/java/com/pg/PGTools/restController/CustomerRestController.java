package com.pg.PGTools.restController;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pg.PGTools.entity.Customers;
import com.pg.PGTools.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
    private CustomerService customerService;
	
	@GetMapping("/get-customer/{id}")
	public ResponseEntity<Customers> getCustomerById(@PathVariable Long id) {
	    return customerService.getCustomerById(id) 
	        .map(ResponseEntity::ok)
	        .orElse(ResponseEntity.notFound().build());
	}

	
	@PostMapping("/update-customer/{id}")
	public ResponseEntity<?> updateCustomer(
	    @PathVariable Long id,
	    @RequestParam("editCustomerName") String name,
	    @RequestParam("editCustomerLocation") String location,
	    @RequestParam(value = "customerLogo", required = false) MultipartFile file) {
	    try {
	        Customers updated = customerService.updateCustomer(id, file, name, location);
	        return ResponseEntity.ok(updated);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Update failed: " + e.getMessage());
	    }
	}
	
	
	@PostMapping("/add-customer")
	public ResponseEntity<?> addCustomer(
	        @RequestParam("customerName") String name,
	        @RequestParam("customerLocation") String location,
	        @RequestParam("customerLogo") MultipartFile file) {
	    try {
	        Customers saved = customerService.saveCustomer(file, name, location);
	        return ResponseEntity.ok(saved);
	    } catch (Exception e) {
	        e.printStackTrace(); // ✅ print actual error
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error saving customer: " + e.getMessage()); // show real error
	    }
	}
	
	@GetMapping("/get-all-customers")
	public ResponseEntity<List<Customers>> getAllCustomers() {
	    List<Customers> customers = customerService.getAllCustomers();
	    return ResponseEntity.ok(customers);
	}
	
	


	
	
	@DeleteMapping("/delete-customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
	    try {
	        customerService.deleteCustomerById(id);
	        return ResponseEntity.ok("Customer deleted successfully.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error deleting customer: " + e.getMessage());
	    }
	}
}
