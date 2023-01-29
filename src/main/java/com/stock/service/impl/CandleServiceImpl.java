package com.stock.service.impl;

import static com.stock.constants.CandleConstants.CALENDER_FILE_PATH;
import static com.stock.constants.CandleConstants.DOUBLE_QUOTES_STRING;
import static com.stock.constants.CandleConstants.FILE_EMPTY;
import static com.stock.constants.CandleConstants.INTEGER_FIVE;
import static com.stock.constants.CandleConstants.NO_DATA_FOUND;
import static com.stock.constants.CandleConstants.OPENING_RANGE_BREAKOUT_MSG;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.entity.CandleEntityList;
import com.stock.exception.StockBadFormatException;
import com.stock.exception.StockNotFoundException;
import com.stock.rest.client.model.Candle;
import com.stock.rest.model.CandleDto;
import com.stock.rest.repository.CandleRepository;
import com.stock.service.CandleService;
import com.stock.util.StockUtil;

/**
 * The Class CandleServiceImpl.
 * 
 * @author om kumar
 */
@Service
public class CandleServiceImpl implements CandleService {

	/** The candle repository. */
	@Autowired
	private CandleRepository candleRepository;

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	/**
	 * Gets the opening range break out.
	 *
	 * @param time the time
	 * @return the opening range break out
	 */
	@Override
	public String getOpeningRangeBreakOut(int time) {
		List<CandleDto> candleDtos = getStockCandles();
		if (time % INTEGER_FIVE != 0) {
			throw new StockBadFormatException("Input time is not a multiple of 5");
		}
		int n = time / INTEGER_FIVE;
		List<Candle> resp = new ArrayList<>();
		StockUtil.dtosListToCandleList(candleDtos, resp);
		Candle candle = resp.get(0);
		for (int i = 1; i < n; i++) {
			candle.setHigh(Math.max(candle.getHigh(), resp.get(i).getHigh()));
			candle.setLow(Math.min(candle.getLow(), resp.get(i).getLow()));
			candle.setClose(resp.get(i).getClose());
		}
		StringBuilder openingRangeBreakOut = new StringBuilder();
		for (int i = n; i < resp.size(); i++) {
			double close = Double.parseDouble(resp.get(i).getClose());
			if (close < candle.getLow() || close > candle.getHigh()) {
				openingRangeBreakOut.append(OPENING_RANGE_BREAKOUT_MSG).append(DOUBLE_QUOTES_STRING)
						.append(resp.get(i).getLastTradeTime()).append(DOUBLE_QUOTES_STRING);
				break;
			}
		}
		return openingRangeBreakOut.toString();
	}

	/**
	 * Gets the combine candles.
	 *
	 * @param time the time
	 * @return the combine candles
	 */
	@Override
	public List<Candle> getCombineCandles(int time) {
		List<CandleDto> candleDtos = getStockCandles();
		List<Candle> resp = new ArrayList<>();
		int partitionVale = time / 5;
		StockUtil.dtosListToCandleList(candleDtos, resp);
		List<List<Candle>> partitionCandles = ListUtils.partition(resp, partitionVale);
		List<Candle> ans = new ArrayList<>();
		AtomicInteger count1 = new AtomicInteger();
		partitionCandles.stream().forEach(candleList -> {
			Candle c = candleList.get(0);
			c.setTradedQty(0);
			c.setId(count1.getAndIncrement());
			candleList.stream().forEach(candle -> {
				c.setHigh(Math.max(c.getHigh(), candle.getHigh()));
				c.setLow(Math.min(c.getLow(), candle.getLow()));
				c.setTradedQty(c.getTradedQty() + candle.getTradedQty());
			});
			ans.add(c);
		});
		return ans;
	}

	/**
	 * Gets the stock candles.
	 *
	 * @return the stock candles
	 */
	@Override
	public List<com.stock.rest.model.CandleDto> getStockCandles() {
		loadData();
		List<com.stock.entity.Candle> candles = candleRepository.findAll();
		if (candles == null) {
			throw new StockNotFoundException(NO_DATA_FOUND);
		}
		List<CandleDto> candleDtos = candles.stream().map(candle -> mapper.map(candle, CandleDto.class))
				.sorted(Comparator.comparing(com.stock.rest.model.CandleDto::getLastTradeTime))
				.collect(Collectors.toList());
		return candleDtos;
	}

	/**
	 * Load data.
	 */
	public void loadData() {
		CandleEntityList candleEntityList = StockUtil.jsonToModel(CALENDER_FILE_PATH,
				com.stock.entity.CandleEntityList.class);
		if (candleEntityList == null) {
			throw new StockNotFoundException(FILE_EMPTY);
		}
		List<com.stock.entity.Candle> candles = candleEntityList.getCandles().stream().collect(Collectors.toList());
		candleRepository.saveAll(candles);
	}

}
