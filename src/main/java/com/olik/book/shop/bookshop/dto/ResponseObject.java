package com.olik.book.shop.bookshop.dto;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

public class ResponseObject<ResponseDataType> {
	private final int status;
	private final String error;
	private final boolean success;
	private final ResponseDataType data;

	public ResponseObject(int status, boolean success, ResponseDataType data, String error) {

		this.status = status;
		this.error = error;
		this.success = success;
		this.data = data;
	}

	public ResponseEntity<ResponseObject<ResponseDataType>> toResponseEntity() {
		return ResponseEntity.status(HttpStatus.valueOf(this.status)).body(this);
	}

	public int getStatus() {
		return this.status;
	}

	public String getError() {
		return this.error;
	}

	public boolean getSuccess() {
		return this.success;
	}

	public ResponseDataType getData() {
		return this.data;
	}

}
