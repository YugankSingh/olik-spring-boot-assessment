package com.olik.book.shop.bookshop.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Rentals")
public class Rental {
	private String id;
	private String bookId;
	private Book book;

	private String renterName;
	private Date rentalDate;
	private Date returnDate;

	public Rental() {
	}

	public Rental(String id, String bookId, String renterName, Date rentalDate, Date returnDate) {
		this.id = id;
		this.bookId = bookId;
		this.renterName = renterName;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getRenterName() {
		return renterName;
	}

	public void setRenterName(String renterName) {
		this.renterName = renterName;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
