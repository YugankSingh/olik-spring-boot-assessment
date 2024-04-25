package com.olik.book.shop.bookshop.dto;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateBookDTO {


	@NotNull
	@Length(max = 250, min = 1, message = "The length of book title must be between 1 and 250")
	public String title;


	@NotNull
	@NotBlank(message = "Author Id can't be blank")
	public String authorId;


	@NotNull
	@ISBN(message = "ISBN format is invalid")
	public String isbn;


	@NotNull
	@Min(value = 0, message = "Year must be a positive number")
	@Max(value = 3000, message = "Year of publication can't be more than 3000")
	public int publicationYear;

}
