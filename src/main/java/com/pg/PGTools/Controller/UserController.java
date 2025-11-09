package com.pg.PGTools.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.pg.PGTools.entity.Customers;
import com.pg.PGTools.entity.InductrieWork;
import com.pg.PGTools.entity.Products;
import com.pg.PGTools.service.CustomerService;
import com.pg.PGTools.service.InductrieWorkService;
import com.pg.PGTools.service.ProductService;
import org.springframework.ui.Model;

@Controller
public class UserController {
	
	@Autowired
    private ProductService productService;
	
	@Autowired
	private InductrieWorkService inductrieWorkService;
	
	@Autowired
    private CustomerService customerService;
	
	
	@GetMapping("/")
	public String homePage(Model model) {
        List<Products> products = productService.getAllProducts();
        model.addAttribute("products", products);
		return "index";
	}
	
	@GetMapping("about")
	public String aboutPage(Model model) {
        List<Products> products = productService.getAllProducts();
        model.addAttribute("products", products);
		return "about";
	}
	
	@GetMapping("/products")
    public String productsPage(Model model) {
        List<Products> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }
	
	@GetMapping("customers")
	public String customersPage(Model model) {
		List<Customers> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
		return "customers";
	}

	@GetMapping("gallery")
	public String galleryPage(Model model) {
	    List<InductrieWork> inductries = inductrieWorkService.getAllInductries();
	    model.addAttribute("inductries", inductries);
		return "gallery";
	}
	
	@GetMapping("contact")
	public String contactPage() {
		return "contact";
	}
}
