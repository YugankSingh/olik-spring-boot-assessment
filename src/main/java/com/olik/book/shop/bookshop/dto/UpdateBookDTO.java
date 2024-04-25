package com.olik.book.shop.bookshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateBookDTO extends CreateBookDTO {
	@NotNull
	@NotBlank(message = "Book id can't be blank")
	public String id;
}
