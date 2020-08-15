package com.backend.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.product.Repositories.ProductRepository;
import java.util.List;
import com.backend.product.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product saveOrUpdatePerson(Product product) {

		// logic
		return productRepository.save(product);

	}

	public boolean putProduct() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProductOperatingHours(String productID) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProductBusinessName(String productID) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean createNewProduct() {
		// TODO Auto-generated method stub
		return false;
	}
}
