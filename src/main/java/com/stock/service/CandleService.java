package com.stock.service;

import java.util.List;

import com.stock.rest.client.model.Candle;

public interface CandleService {
	String getOpeningRangeBreakOut(int time);

	List<Candle> getCombineCandles(int time);
	
	List<com.stock.rest.model.CandleDto> getStockCandles();

}
