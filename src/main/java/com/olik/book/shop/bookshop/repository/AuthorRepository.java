package com.olik.book.shop.bookshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.olik.book.shop.bookshop.model.Author;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

}
