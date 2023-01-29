package com.stock.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandleEntityList {

	/** The candles. */
	@JsonProperty("candles")
	private List<Candle> candles;
}
