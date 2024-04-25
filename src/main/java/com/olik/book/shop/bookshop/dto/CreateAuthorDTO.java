package com.olik.book.shop.bookshop.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;

public class CreateAuthorDTO {


	@NotNull
	@Length(max = 250, min = 1, message = "The length of Author name must be between 1 and 250")
	public String name;

	@NotNull
	@Length(max = 2000, min = 1, message = "The length of Author biography must be between 1 and 2000")
	public String biography;

}

