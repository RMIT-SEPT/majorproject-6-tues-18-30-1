package com.example.demo.backend.product.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.backend.product.model.Bookking;




public interface BookingRepository extends CrudRepository<Bookking,String> {

	

	

	Bookking findAllById(String id);

	void delete(String id);

	

}
