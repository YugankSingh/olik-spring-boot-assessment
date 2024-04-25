package com.olik.book.shop.bookshop.controller;

import org.springframework.web.bind.annotation.RestController;

import com.olik.book.shop.bookshop.dto.CreateAuthorDTO;
import com.olik.book.shop.bookshop.dto.ResponseObject;
import com.olik.book.shop.bookshop.dto.UpdateAuthorDTO;
import com.olik.book.shop.bookshop.exception.BusinesLogicException;
import com.olik.book.shop.bookshop.model.Author;
import com.olik.book.shop.bookshop.service.AuthorService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthorController {

	@Autowired
	AuthorService service;

	@GetMapping("/author/{id}")
	public ResponseEntity<ResponseObject<Author>> getAuthor(@PathVariable String id) throws BusinesLogicException {
		Author author = service.get(id);
		if (author == null)
			throw new Error("Invalid Author Id, doesn't exist");
		return (new ResponseObject<Author>(200, true, author, null)).toResponseEntity();
	}

	@PostMapping("/author")
	public ResponseEntity<ResponseObject<Author>> createAuthor(@RequestBody @Valid CreateAuthorDTO data)
			throws BusinesLogicException {
		Author author = service.create(
				new Author(null, data.name, data.biography));
		return (new ResponseObject<Author>(200, true, author, null)).toResponseEntity();
	}

	@PatchMapping("/author/{authorId}")
	public ResponseEntity<ResponseObject<Author>> updateAuthor(@RequestBody @Valid UpdateAuthorDTO data,
			@PathVariable String authorId)
			throws BusinesLogicException {

		if (!data.id.equals(authorId))
			throw new BusinesLogicException("Author id in path variable and in object are different");

		Author author = service.update(
				new Author(null, data.name, data.biography), authorId);
		return (new ResponseObject<Author>(200, true, author, null)).toResponseEntity();
	}

	@DeleteMapping("/author/{id}")
	public ResponseEntity<ResponseObject<Author>> deleteBook(@PathVariable String id)
			throws BusinesLogicException {
		Author author = service.delete(id);
		return (new ResponseObject<Author>(200, true, author, null)).toResponseEntity();
	}

}
