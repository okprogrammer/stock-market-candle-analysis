package com.stock.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.rest.client.model.Candle;
import com.stock.rest.model.CandleDto;

/**
 * The Class StockUtil.
 * 
 * @author om kumar
 */
public class StockUtil {

	/** The Constant MAPPER. */
	private static final ObjectMapper MAPPER = new ObjectMapper()
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	/**
	 * Json to model.
	 *
	 * @param <T>      the generic type
	 * @param basePath the base path
	 * @param type     the type
	 * @return the t
	 */
	public static <T> T jsonToModel(String basePath, Class<T> type) {
		try {
			File file = new ClassPathResource(basePath).getFile();
			String data = new String(Files.readAllBytes(file.toPath()));
			return MAPPER.readValue(data, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Dtos list to candle list.
	 *
	 * @param dtoList the dto list
	 * @param candles the candles
	 */
	public static void dtosListToCandleList(List<CandleDto> dtoList, List<Candle> candles) {
		AtomicInteger count = new AtomicInteger();
		dtoList.stream().forEach(e -> {
			candles.add(Candle.builder().id(count.getAndIncrement()).lastTradeTime(e.getLastTradeTime())
					.low(Double.parseDouble(e.getLow().trim())).high(Double.parseDouble(e.getHigh().trim()))
					.close(e.getClose()).open(e.getOpen()).quotationLot(e.getQuotationLot())
					.tradedQty(Double.parseDouble(e.getTradedQty())).openInterest(e.getOpenInterest()).build());
		});
	}

}
