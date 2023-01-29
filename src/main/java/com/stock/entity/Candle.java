package com.stock.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Candle.
 * 
 * @author om kumar
 */
@Entity
@Table(name = "candles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candle {

	/** The last trade time. */
	@Id
	@JsonProperty("LastTradeTime")
	private String lastTradeTime;

	/** The quotation lot. */
	@JsonProperty("QuotationLot")
	private String quotationLot;

	/** The traded qty. */
	@JsonProperty("TradedQty")
	private String tradedQty;

	/** The open interest. */
	@JsonProperty("OpenInterest")
	private String openInterest;

	/** The open. */
	@JsonProperty("Open")
	private String open;

	/** The high. */
	@JsonProperty("High")
	private String high;

	/** The low. */
	@JsonProperty("Low")
	private String low;

	/** The close. */
	@JsonProperty("Close")
	private String close;
}
