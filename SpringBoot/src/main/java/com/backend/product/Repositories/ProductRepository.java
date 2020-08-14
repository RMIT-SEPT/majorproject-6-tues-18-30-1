package com.backend.product.Repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
import com.backend.product.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Override
    Iterable<Product> findAllById(Iterable<Long> iterable);

	Product save(Product product);
}