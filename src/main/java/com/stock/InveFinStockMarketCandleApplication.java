package com.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stock.rest.model.Candles;
import com.stock.util.StockUtil;

@SpringBootApplication
public class InveFinStockMarketCandleApplication {

	public static void main(String[] args) {
		SpringApplication.run(InveFinStockMarketCandleApplication.class, args);
	}

}
