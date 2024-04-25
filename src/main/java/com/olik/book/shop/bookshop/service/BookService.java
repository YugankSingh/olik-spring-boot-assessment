package com.olik.book.shop.bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olik.book.shop.bookshop.exception.BusinesLogicException;
import com.olik.book.shop.bookshop.model.Author;
import com.olik.book.shop.bookshop.model.Book;
import com.olik.book.shop.bookshop.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository repository;
	@Autowired
	AuthorService authorService;

	public Book get(String id) throws BusinesLogicException {
		if (id == null)
			throw new Error("Book id can't be null.");

		Book book = repository.findById(id).orElse(null);
		if (book == null)
			throw new BusinesLogicException("Book with id " + id + " does not exist!");

		Author author = authorService.get(book.getAuthorId());
		if (author == null)
			throw new BusinesLogicException("Author not found, invalid Author ID");
		book.setAuthor(author);

		return book;
	}

	public Book create(Book book) throws BusinesLogicException {
		Author author = authorService.get(book.getAuthorId());

		if (author == null)
			throw new BusinesLogicException("Author not found, invalid Author ID");

		book.setAuthorId(author.getId());
		book.setIsRented(false);

		Book returnedBook = repository.save(book);
		returnedBook.setAuthor(author);
		return returnedBook;
	}

	public Book update(Book book, String id) throws BusinesLogicException {

		Book existingBook = repository.findById(id).orElse(null);
		if (existingBook == null)
			throw new BusinesLogicException("Book with id " + id + " does not exist!");

		Author author = authorService.get(book.getAuthorId());

		if (author == null)
			throw new BusinesLogicException("Author not found, invalid Author ID");
		if (existingBook.getIsRented() != book.getIsRented())
			throw new BusinesLogicException("Rented field can't be changed using update");

		book.setAuthorId(author.getId());
		book.setAuthor(null);
		book.setId(id);

		Book returnedBook = repository.save(book);
		returnedBook.setAuthor(author);
		return returnedBook;
	}

	public Book delete(String id) throws BusinesLogicException {
		Book book = repository.findById(id).orElse(null);
		if (book == null)
			throw new BusinesLogicException("Book with id " + id + " does not exist!");
		repository.deleteById(id);
		return book;
	}

	public Book setIsRented(Book book, boolean isRented) throws BusinesLogicException {
		book.setIsRented(isRented);
		book.setAuthor(null);
		return repository.save(book);
	}

	public Iterable<Book> getByAuthor(String authorId) throws BusinesLogicException {
		Author author = authorService.get(authorId);
		if (author == null)
			throw new BusinesLogicException("Author not found, invalid Author ID");

		Book booksToFetch = new Book();
		booksToFetch.setAuthorId(authorId);
		Iterable<Book> books = repository.findByAuthorId(authorId);
		return books;
	}

	public Iterable<Book> getByIsRented(boolean isRented) {
		Book booksToFetch = new Book();
		booksToFetch.setIsRented(isRented);
		Iterable<Book> books = repository.findByIsRented(isRented);
		return books;
	}

}
