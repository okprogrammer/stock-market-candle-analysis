package com.stock.rest.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.Entity;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candle {

	private int id;

	private String lastTradeTime;

	private String quotationLot;

	private double tradedQty;

	private String openInterest;

	private String open;

	private double high;

	private double low;

	private String close;
}
