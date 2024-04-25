package com.olik.book.shop.bookshop.controller;

import org.springframework.web.bind.annotation.RestController;

import com.olik.book.shop.bookshop.dto.CreateBookDTO;
import com.olik.book.shop.bookshop.dto.ResponseObject;
import com.olik.book.shop.bookshop.dto.UpdateBookDTO;
import com.olik.book.shop.bookshop.exception.BusinesLogicException;
import com.olik.book.shop.bookshop.model.Book;
import com.olik.book.shop.bookshop.service.BookService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BookController {

	@Autowired
	BookService service;

	@GetMapping("/book/{id}")
	public ResponseEntity<ResponseObject<Book>> getBook(@PathVariable String id) throws BusinesLogicException {
		Book book = service.get(id);
		if (book == null)
			throw new Error("Invalid Book Id, doesn't exist");

		return (new ResponseObject<Book>(200, true, book, null)).toResponseEntity();
	}

	@PostMapping("/book")
	public ResponseEntity<ResponseObject<Book>> createBook(@RequestBody @Valid CreateBookDTO data)
			throws BusinesLogicException {
		Book book = service.create(
				new Book(null, data.title, data.authorId, data.isbn, data.publicationYear));
		return (new ResponseObject<Book>(200, true, book, null)).toResponseEntity();
	}

	@PatchMapping("/book/{id}")
	public ResponseEntity<ResponseObject<Book>> updateBook(@RequestBody @Valid UpdateBookDTO data,
			@PathVariable String id)
			throws BusinesLogicException {

		if (!data.id.equals(id))
			throw new BusinesLogicException("Book id in path variable and in object are different");

		Book book = service.update(
				new Book(id, data.title, data.authorId, data.isbn, data.publicationYear), id);
		return (new ResponseObject<Book>(200, true, book, null)).toResponseEntity();
	}

	@DeleteMapping("/book/{id}")
	public ResponseEntity<ResponseObject<Book>> deleteBook(@PathVariable String id)
			throws BusinesLogicException {
		Book book = service.delete(id);
		return (new ResponseObject<Book>(200, true, book, null)).toResponseEntity();
	}

	@GetMapping("/book/by-author/{authorId}")
	public ResponseEntity<ResponseObject<Iterable<Book>>> getBooksByAuthor(@PathVariable String authorId)
			throws BusinesLogicException {
		Iterable<Book> books = service.getByAuthor(authorId);
		return (new ResponseObject<Iterable<Book>>(200, true, books, null)).toResponseEntity();
	}

	@GetMapping("/book/get-all-rented")
	public ResponseEntity<ResponseObject<Iterable<Book>>> getRentedBooks()
			throws BusinesLogicException {
		Iterable<Book> books = service.getByIsRented(true);
		return (new ResponseObject<Iterable<Book>>(200, true, books, null)).toResponseEntity();
	}

	@GetMapping("/book/get-all-not-rented")
	public ResponseEntity<ResponseObject<Iterable<Book>>> getRentableBooks()
			throws BusinesLogicException {
				Iterable<Book> books = service.getByIsRented(false);
				return (new ResponseObject<Iterable<Book>>(200, true, books, null)).toResponseEntity();
	}

}
