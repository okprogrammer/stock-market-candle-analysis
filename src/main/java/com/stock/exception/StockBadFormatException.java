package com.stock.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockBadFormatException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The message. */
	private String message;

	/**
	 * Instantiates a new stock bad format exception.
	 *
	 * @param message the message
	 */
	public StockBadFormatException(String message) {
		super(message);
		this.message = message;
	}
}
