package com.backend.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.backend.model.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
//	List<User> findByName(String firstName, String lastName);
//	
//	User findById(@Param("id") long id);
//	
//	Boolean addUser(User user);
//	
//	Boolean deleteUserById(@Param("id") long id);
//	
//	Boolean updateUserAddress(@Param("id") long id, @Param("street-number") String streetNumber, @Param("street-name") String streetName, @Param("suburb") String suburb, @Param("postcode") int postcode, @Param("state") String state);
}
