package com.olik.book.shop.bookshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateAuthorDTO extends CreateAuthorDTO {
	@NotNull
	@NotBlank(message = "Author Id can't be blank")
	public String id;
}
