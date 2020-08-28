package com.backend.repositories;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.backend.model.Booking;
import com.backend.model.User;

@RepositoryRestResource(collectionResourceRel = "bookings", path = "bookings")
public interface BookingRepository extends PagingAndSortingRepository<User, Long>{
//	List<Booking> findByName(@Param("first-name") String firstName, @Param("last-name") String lastName);
//	Booking findById(@Param("id") long id);
//	Boolean addUserToBooking(User user);
//	Boolean deleteBookingById(@Param("id") long id);
//	Boolean updateBookingLength(@Param("booking-length") double bookingLength);
}

