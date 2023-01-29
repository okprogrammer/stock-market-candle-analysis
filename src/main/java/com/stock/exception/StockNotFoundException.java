package com.stock.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The message. */
	private String message;

	public StockNotFoundException(String message) {
		super(message);
		this.message = message;
	}

}
