package com.olik.book.shop.bookshop.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.olik.book.shop.bookshop.dto.ResponseObject;
import com.olik.book.shop.bookshop.exception.BusinesLogicException;

@RestControllerAdvice
public class ExcpetionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseObject<Object>> handleInvalidArgument(MethodArgumentNotValidException ex) {

		StringBuilder completeError = new StringBuilder();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			completeError.append(error.getField() + ": " + error.getDefaultMessage() + ", ");
		});
		return (new ResponseObject<>(400, false, null, completeError.toString())).toResponseEntity();
	}

	@ExceptionHandler(BusinesLogicException.class)
	public ResponseEntity<ResponseObject<Object>> handleBusinessLogicException(BusinesLogicException ex) {
		return (new ResponseObject<>(
				ex.getStatusCode(),
				false,
				null,
				ex.getMessage()))
				.toResponseEntity();
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseObject<Object>> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException ex) {
		return (new ResponseObject<>(
				400,
				false,
				null,
				"Required request body is missing"))
				.toResponseEntity();
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ResponseObject<Object>> handleNotFoundException(
			NoResourceFoundException ex) {
		return (new ResponseObject<>(
				404,
				false,
				null,
				"Invalid request url!"))
				.toResponseEntity();
	}

	// @ExceptionHandler(Exception.class)
	// public ResponseEntity<ResponseObject<Object>> handleAllException(Exception
	// ex) {
	// System.out.println(ex.getMessage());
	// ex.printStackTrace();
	// return (new ResponseObject<>(
	// 500,
	// false,
	// null,
	// "Internal Server Error"))
	// .toResponseEntity();
	// }

}
