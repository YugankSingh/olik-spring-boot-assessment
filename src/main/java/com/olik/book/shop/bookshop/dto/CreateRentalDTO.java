package com.olik.book.shop.bookshop.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateRentalDTO {
	@NotNull
	@NotBlank(message = "Book ID is required")
	public String bookId;

	@NotNull
	@NotBlank(message = "Renter name is required")
	public String renterName;

}
