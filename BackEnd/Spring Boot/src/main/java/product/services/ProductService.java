package product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.Repositories.ProductRepository;
import product.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product saveOrUpdatePerson(Product product) {

		// logic
		return productRepository.save(product);

	}
}
