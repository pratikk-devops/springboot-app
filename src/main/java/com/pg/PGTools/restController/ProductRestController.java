package com.pg.PGTools.restController;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pg.PGTools.entity.Products;
import com.pg.PGTools.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ProductRestController {

	@Autowired
    private ProductService productService;

//	Add Product
    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestParam("productName") String productName,
                                             @RequestParam("productImage") MultipartFile productImage,
                                             HttpServletRequest request) {
        try {
            // Save image
//            String uploadDir = new File("src/main/resources/static/images/ProductImages").getAbsolutePath();
        	String uploadDir = "/home/ubuntu/project/images/ProductImages";
            String fileName = productImage.getOriginalFilename();
            File saveFile = new File(uploadDir, fileName);
            productImage.transferTo(saveFile);

            // Save to DB
            Products product = new Products();
            product.setProductName(productName);
            product.setProductImage("/images/ProductImages/" + fileName);
            productService.saveProduct(product);

            return ResponseEntity.ok("Product added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product.");
        }
    }
    
//  All Products
    @GetMapping("/products")
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

//  Get Product Id
    @GetMapping("/products/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        Products product = productService.getProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

//  Update Product
    @PostMapping("/products/update")
    public ResponseEntity<String> updateProduct(@RequestParam("id") Long id,
                                                @RequestParam("editproductName") String productName,
                                                @RequestParam(value = "editproductImage", required = false) MultipartFile productImage) {
        try {
            Products existingProduct = productService.getProductById(id);
            if (existingProduct == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            }

            existingProduct.setProductName(productName);

            if (productImage != null && !productImage.isEmpty()) {
                String uploadDir = new File("src/main/resources/static/images/ProductImages").getAbsolutePath();
                String fileName = productImage.getOriginalFilename();
                File saveFile = new File(uploadDir, fileName);
                productImage.transferTo(saveFile);
                existingProduct.setProductImage("/images/ProductImages/" + fileName);
            }

            productService.saveProduct(existingProduct);
            return ResponseEntity.ok("Product updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update product.");
        }
    }

//  Delete Product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product");
        }
    }

    
}
