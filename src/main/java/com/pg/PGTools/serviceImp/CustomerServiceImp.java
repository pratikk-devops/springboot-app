package com.pg.PGTools.serviceImp;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pg.PGTools.entity.Customers;
import com.pg.PGTools.repository.CustomerRepository;
import com.pg.PGTools.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
    private CustomerRepository customerRepository;
	
//	private final String uploadDir = new File("src/main/resources/static/images/CustomerLogo").getAbsolutePath();
	private final String uploadDir = "/home/ubuntu/project/images/CustomerLogo";

	@Override
    public Optional<Customers> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customers updateCustomer(Long id, MultipartFile file, String name, String location) throws IOException {
        Optional<Customers> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()) {
            throw new RuntimeException("Customer not found with ID: " + id);
        }

        Customers customer = optionalCustomer.get();
        customer.setCustomerName(name);
        customer.setCustomerLocation(location);

        if (file != null && !file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String filePath = uploadDir + "/" + originalFilename;
            file.transferTo(new File(filePath));
            customer.setCustomerLogo("/images/CustomerLogo/" + originalFilename);
        }

        return customerRepository.save(customer);
    }
    
	    @Override
	    public Customers saveCustomer(MultipartFile file, String name, String location) throws IOException {
	        File savePath = new File(uploadDir);
	        if (!savePath.exists()) {
	            savePath.mkdirs();
	        }

	        String originalFilename = file.getOriginalFilename();
	        String filePath = uploadDir + "/" + originalFilename;
	        file.transferTo(new File(filePath));

	        Customers customer = new Customers();
	        customer.setCustomerName(name);
	        customer.setCustomerLocation(location);
	        customer.setCustomerLogo("/images/CustomerLogo/" + originalFilename);

	        return customerRepository.save(customer);
	    }
	    
	    @Override
	    public List<Customers> getAllCustomers() {
	        return customerRepository.findAll();
	    }
	    
	    @Override
	    public void deleteCustomerById(Long id) {
	        customerRepository.deleteById(id);
	    }
}
