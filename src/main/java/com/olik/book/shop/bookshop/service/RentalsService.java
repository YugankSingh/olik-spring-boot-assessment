package com.olik.book.shop.bookshop.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olik.book.shop.bookshop.dto.CreateRentalDTO;
import com.olik.book.shop.bookshop.exception.BusinesLogicException;
import com.olik.book.shop.bookshop.model.Book;
import com.olik.book.shop.bookshop.model.Rental;
import com.olik.book.shop.bookshop.repository.RentalsRepository;

@Service
public class RentalsService {

	@Autowired
	RentalsRepository repository;

	@Autowired
	BookService bookService;

	public Rental create(CreateRentalDTO createRentalDTO) throws BusinesLogicException {
		Rental rental = new Rental();
		Book book = bookService.get(createRentalDTO.bookId);
		Date rentalDate = new Date();

		if (book.getIsRented())
			throw new BusinesLogicException("The Book is already rented");

		rental.setBookId(book.getId());
		rental.setRenterName(createRentalDTO.renterName);
		rental.setRentalDate(rentalDate);

		Book newBook = bookService.setIsRented(book, true);
		Rental newRental = repository.save(rental);
		newRental.setBook(newBook);
		return newRental;
	}

	public Rental get(String rentalId) throws BusinesLogicException {
		Rental rental = repository.findById(rentalId).orElse(null);
		if (rental == null)
			throw new BusinesLogicException("The rental with rentalId " + rentalId + " does not exist!");
		return rental;
	}

	public Iterable<Rental> getAll() {
		Iterable<Rental> rental = repository.findAll();
		return rental;
	}

	public List<Rental> getRentalsMoreThanDaysAgo(int days) {
		Date daysAgo = new Date(System.currentTimeMillis() - days * 24 * 60 * 60 * 1000);
		return repository.findByUnreturnedRentalDateBefore(daysAgo);
	}

	public Rental returnBook(String rentalId) throws BusinesLogicException {
		Rental rental = repository.findById(rentalId).orElse(null);
		if (rental == null)
			throw new BusinesLogicException("The rental with rentalId " + rentalId + " does not exist!");
		if (rental.getReturnDate() != null)
			throw new BusinesLogicException("You have already returned the book!");

		Book book = bookService.get(rental.getBookId());
		Date returnDate = new Date();

		if (!book.getIsRented())
			throw new BusinesLogicException("The Book was never rented");

		rental.setReturnDate(returnDate);

		Book newBook = bookService.setIsRented(book, false);
		Rental returnedRental = repository.save(rental);
		returnedRental.setBook(newBook);
		return returnedRental;
	}

}
