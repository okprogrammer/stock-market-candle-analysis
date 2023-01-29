package com.stock.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "candles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candle {

	@Id
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
