package com.olik.book.shop.bookshop.exception;

public class BusinesLogicException extends Exception {
	private final int statusCode;

	public BusinesLogicException(String message) {
		super(message);
		this.statusCode = 400;
	}

	public BusinesLogicException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

}
