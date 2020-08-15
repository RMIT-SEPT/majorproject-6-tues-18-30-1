package com.backend.product.api;

import com.backend.product.model.Product;
import com.backend.product.services.ProductService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class RestAPIController {

	@Autowired
	ProductService productService;

	@PostMapping("/product")
	public boolean createNewProduct() {
		return productService.createNewProduct();
	}

	@PutMapping("/product/{operating-hours}")
	public String updateOperatingHours(@PathVariable("operating-hours") String operatingHours) {
		return productService.getProductOperatingHours(operatingHours);
	}

	@GetMapping("/product")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/product/{operating-hours}")
	public String getProductOperatingHours(@PathVariable("product-id") String productID) {
		return productService.getProductOperatingHours(productID);
	}

	@GetMapping("/product/{business-name}")
	public String getProductBusinessName(@PathVariable("product-id") String productID) {
		return productService.getProductBusinessName(productID);
	}

}
