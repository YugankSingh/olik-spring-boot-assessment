package com.olik.book.shop.bookshop.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.olik.book.shop.bookshop.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
	List<Book> findByAuthorId(String authorId);
	List<Book> findByIsRented(boolean isRented);
}
