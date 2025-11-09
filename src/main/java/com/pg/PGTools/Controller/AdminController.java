package com.pg.PGTools.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pg.PGTools.entity.Admin;
import com.pg.PGTools.repository.AdminRepository;
import com.pg.PGTools.service.ContactService;
import com.pg.PGTools.service.ProductService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class AdminController {
	
//	Admin Repository
	 @Autowired
	 private AdminRepository adminRepository;
	 
	 @Autowired
	 private ProductService productService;

	 @Autowired
	 private ContactService contactService;
	 
	@GetMapping("/admin/dashboard")
	public String dashboardPage(HttpSession session, Model model) {
        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/admin/signIn";
        }
        
        int productCount = productService.getAllProducts().size();
        int contactCount = contactService.getAllContacts().size();

        model.addAttribute("productCount", productCount);
        model.addAttribute("contactCount", contactCount);
        
		return "Admin/dashboard";
	}
	
	@GetMapping("/admin")
	public String adminPage(HttpSession session) {
		if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/admin/signIn";
        }
		return "Admin/template";
	}
	
//	SuccessFull Admin Login
	@GetMapping("/admin/signIn")
	public String signInPage() {
		return "Admin/sign-in";
	}
	
	@PostMapping("/admin/signIn")
    public String loginAdmin(@RequestParam String email,
                             @RequestParam String password,
                             HttpSession session,
                             Model model) {
        Optional<Admin> optionalAdmin = adminRepository.findByAdminEmail(email);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            if (admin.getAdminPassword().equals(password)) {
                session.setAttribute("adminLoggedIn", true);
                return "redirect:/admin/dashboard";
            } else {
                model.addAttribute("error", "Invalid password");
            }
        } else {
            model.addAttribute("error", "Admin not found");
        }
        return "Admin/sign-in";
    }
	
	@GetMapping("/admin/userQuery")
	public String userQueryPage(HttpSession session) {
        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/admin/signIn";
        }
		return "Admin/userQuery";
	}
	
	@GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/signIn";
    }
	
//	Admin Sign Up Page
	@GetMapping("/admin/signUp")
	public String signUpPage(Model model, HttpSession session) {
        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/admin/signIn";
        }
		List<Admin> admins = adminRepository.findAll();
        if (!admins.isEmpty()) {
            model.addAttribute("admin", admins.get(0));
        } else {
            model.addAttribute("admin", new Admin());
        }
		return "Admin/sign-up";
	}
	
//	Add & All Products
	@GetMapping("/admin/adminProducts")
	public String adminProductPage(HttpSession session) {
        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/admin/signIn";
        }
		return "Admin/adminProducts";
	}
	
//  Add & All Customers
	@GetMapping("/admin/adminCustomers")
	public String adminCustomersPage(HttpSession session) {
        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/admin/signIn";
        }
		return "Admin/adminCustomers";
	}
	
//	Admin Gallery
	@GetMapping("/admin/adminInductries")
	public String adminGalleryPage(HttpSession session) {
        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/admin/signIn";
        }
		return "Admin/adminInductries";
	}

}
