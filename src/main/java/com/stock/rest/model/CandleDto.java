package com.stock.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandleDto {

	@JsonProperty("LastTradeTime")
	private String lastTradeTime;

	@JsonProperty("QuotationLot")
	private String quotationLot;

	@JsonProperty("TradedQty")
	private String tradedQty;

	@JsonProperty("OpenInterest")
	private String openInterest;

	@JsonProperty("Open")
	private String open;

	@JsonProperty("High")
	private String high;

	@JsonProperty("Low")
	private String low;

	@JsonProperty("Close")
	private String close;
}
