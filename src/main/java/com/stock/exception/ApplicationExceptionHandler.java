package com.stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stock.exception.payload.ApiExceptionPayload;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(StockNotFoundException.class)
	public ResponseEntity<ApiExceptionPayload> resourceNotFoundExceptionHandler(StockNotFoundException ex) {
		return new ResponseEntity<ApiExceptionPayload>(ApiExceptionPayload.builder().message(ex.getMessage()).build(),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(StockBadFormatException.class)
	public ResponseEntity<ApiExceptionPayload> resourceNotFoundExceptionHandler(StockBadFormatException ex) {
		return new ResponseEntity<ApiExceptionPayload>(ApiExceptionPayload.builder().message(ex.getMessage()).build(),
				HttpStatus.BAD_REQUEST);
	}
}
