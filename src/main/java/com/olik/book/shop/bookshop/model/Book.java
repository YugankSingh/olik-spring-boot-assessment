package com.olik.book.shop.bookshop.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Books")
public class Book {
	private String id;
	private Author author;
	private String title;
	private String authorId;
	private String isbn;
	private int publicationYear;
	private boolean isRented;

	public Book() {
	}

	public Book(String id, String title, String authorId, String isbn, int publicationYear) {
		this.id = id;
		this.title = title;
		this.authorId = authorId;
		this.isbn = isbn;
		this.publicationYear = publicationYear;
	}

	public boolean getIsRented() {
		return this.isRented;
	}

	public void setIsRented(boolean isRented) {
		this.isRented = isRented;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
}
