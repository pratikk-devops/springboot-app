package com.pg.PGTools.restController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pg.PGTools.entity.Admin;
import com.pg.PGTools.repository.AdminRepository;
import com.pg.PGTools.service.AdminService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class AdminRestController {

	@Autowired
    private AdminService adminService;
	
	@Autowired
	 private AdminRepository adminRepository;	
	
//	Admin Register
    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) {
    	if (adminRepository.count() > 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Admin already exists!");
        }
        adminService.saveAdmin(admin);
        return ResponseEntity.ok("Admin Registered Successfully!");
    }
    
//  Admin Update
    @PostMapping("/update")
    public ResponseEntity<String> updateAdmin(@RequestBody Admin admin) {
        Admin existingAdmin = adminRepository.findById(admin.getId())
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        
        existingAdmin.setAdminName(admin.getAdminName());
        existingAdmin.setAdminEmail(admin.getAdminEmail());
        existingAdmin.setAdminPassword(admin.getAdminPassword());

        adminRepository.save(existingAdmin);
        return ResponseEntity.ok("Admin updated successfully!");
    }
    
//  SuccessFull Admin Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Admin loginAdmin, HttpSession session) {
        Optional<Admin> existingAdminOpt = adminRepository.findByAdminEmail(loginAdmin.getAdminEmail());

        if (existingAdminOpt.isPresent()) {
            Admin existingAdmin = existingAdminOpt.get();

            // Simple plain text check (you can use BCrypt later)
            if (existingAdmin.getAdminPassword().equals(loginAdmin.getAdminPassword())) {
                session.setAttribute("loggedInAdmin", existingAdmin.getId());
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
    }

}
