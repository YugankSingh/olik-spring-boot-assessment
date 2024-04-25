package com.olik.book.shop.bookshop.controller;

import org.springframework.web.bind.annotation.RestController;

import com.olik.book.shop.bookshop.dto.CreateRentalDTO;
import com.olik.book.shop.bookshop.dto.ResponseObject;
import com.olik.book.shop.bookshop.exception.BusinesLogicException;
import com.olik.book.shop.bookshop.model.Rental;
import com.olik.book.shop.bookshop.service.RentalsService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class RentalsController {

	@Autowired
	RentalsService service;

	@PostMapping("/rentals/rent-book")
	public ResponseEntity<ResponseObject<Rental>> postMethodName(@RequestBody @Valid CreateRentalDTO data)
			throws BusinesLogicException {
		Rental rental = service.create(data);
		return (new ResponseObject<Rental>(200, true, rental, null)).toResponseEntity();
	}

	@GetMapping("/rental/{rentalId}")
	public ResponseEntity<ResponseObject<Rental>> getRentalsDetails(@PathVariable String rentalId)
			throws BusinesLogicException {
		Rental rental = service.get(rentalId);
		return (new ResponseObject<Rental>(200, true, rental, null)).toResponseEntity();
	}

	@GetMapping("/rentals/fetch-all")
	public ResponseEntity<ResponseObject<Iterable<Rental>>> getAllRentals() {
		Iterable<Rental> rentals = service.getAll();
		return (new ResponseObject<Iterable<Rental>>(200, true, rentals, null)).toResponseEntity();
	}

	@GetMapping("/rentals/fetch-rented-before-days/{days}")
	public ResponseEntity<ResponseObject<Iterable<Rental>>> getRentedBeforeDays(@PathVariable int days) {
		Iterable<Rental> rentals = service.getRentalsMoreThanDaysAgo(days);
		return (new ResponseObject<Iterable<Rental>>(200, true, rentals, null)).toResponseEntity();
	}

	@PostMapping("rentals/return-book/{rentalId}")
	public ResponseEntity<ResponseObject<Rental>> postMethodName(@PathVariable String rentalId)
			throws BusinesLogicException {
		Rental rental = service.returnBook(rentalId);
		return (new ResponseObject<Rental>(200, true, rental, null)).toResponseEntity();
	}

}
