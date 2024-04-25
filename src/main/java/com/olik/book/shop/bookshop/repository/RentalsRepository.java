package com.olik.book.shop.bookshop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.olik.book.shop.bookshop.model.Rental;

@Repository
public interface RentalsRepository extends MongoRepository<Rental, String> {
	@Query("{'rentalDate': {$lt : ?0}, 'returnDate': null}")
	List<Rental> findByUnreturnedRentalDateBefore(Date date);
}
