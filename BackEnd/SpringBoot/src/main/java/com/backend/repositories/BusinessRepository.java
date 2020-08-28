package com.backend.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.backend.model.Business;
import com.backend.model.User;

@RepositoryRestResource(collectionResourceRel = "businesses", path = "businesses")
public interface BusinessRepository extends PagingAndSortingRepository<Business, Long> {

//    @Override
//    Iterable<Business> findAllById(Iterable<Long> iterable);
//    
//    User findById(@Param("id") long id);
//
//	Business save(Business business);
	
}