package com.olik.book.shop.bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olik.book.shop.bookshop.exception.BusinesLogicException;
import com.olik.book.shop.bookshop.model.Author;
import com.olik.book.shop.bookshop.repository.AuthorRepository;

import jakarta.validation.Valid;

@Service
public class AuthorService {

	@Autowired
	AuthorRepository repository;

	public Author get(String id) throws BusinesLogicException {
		Author author = repository.findById(id).orElse(null);
		if (author == null)
			throw new BusinesLogicException("Author with id " + id + " does not exist!");
		return author;
	}

	public Author create(@Valid Author author) throws RuntimeException {
		if (author == null)
			throw new RuntimeException("Invalid author, author can't be null");
		return repository.save(author);
	}

	public Author update(Author author, String authorId) throws BusinesLogicException {
		Author existingAuthor = repository.findById(authorId).orElse(null);
		if (existingAuthor == null)
			throw new BusinesLogicException("Author with id " + authorId + " does not exist!");

		author.setId(authorId);
		return repository.save(author);
	}

	public Author delete(String authorId) throws BusinesLogicException {
		Author author = repository.findById(authorId).orElse(null);
		if (author == null)
			throw new BusinesLogicException("Author with id " + authorId + " does not exist!");

		repository.deleteById(authorId);
		return author;
	}

}
