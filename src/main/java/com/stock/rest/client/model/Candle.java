package com.stock.rest.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candle {

	/** The id. */
	private int id;

	/** The last trade time. */
	private String lastTradeTime;

	/** The quotation lot. */
	private String quotationLot;

	/** The traded qty. */
	private double tradedQty;

	/** The open interest. */
	private String openInterest;

	/** The open. */
	private String open;

	/** The high. */
	private double high;

	/** The low. */
	private double low;

	/** The close. */
	private String close;
}
