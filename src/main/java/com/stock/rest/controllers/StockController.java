package com.stock.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.rest.model.CandleDto;
import com.stock.service.CandleService;

/**
 * The Class StockController.
 * 
 * @author omkumar
 */
@RestController
@RequestMapping(value = "/app", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {

	/** The candle service. */
	@Autowired
	private CandleService candleService;

	/**
	 * Gets the opening range breakout.
	 *
	 * @param time the time
	 * @return the opening range breakout
	 */
	@GetMapping("/stock/opening-break-out/{time}")
	public ResponseEntity<String> getOpeningRangeBreakout(@PathVariable("time") Integer time) {
		String openingRangeBreakOut = candleService.getOpeningRangeBreakOut(time);
		return new ResponseEntity<>(openingRangeBreakOut, HttpStatus.OK);
	}

	/**
	 * Gets the climbes candles.
	 *
	 * @param time the time
	 * @return the climbes candles
	 */
	@GetMapping("/stock/get-combine-candles/{time}")
	public ResponseEntity<List<com.stock.rest.client.model.Candle>> getCombinesCandles(
			@PathVariable("time") Integer time) {
		List<com.stock.rest.client.model.Candle> combineCandles = candleService.getCombineCandles(time);
		return new ResponseEntity<>(combineCandles, HttpStatus.OK);
	}

	/**
	 * Gets the stock candles.
	 *
	 * @return the stock candles
	 */
	@GetMapping("/get/stock-candles")
	public ResponseEntity<List<CandleDto>> getStockCandles() {
		List<CandleDto> stockCandles = candleService.getStockCandles();
		return new ResponseEntity<List<CandleDto>>(stockCandles, HttpStatus.OK);
	}

}
