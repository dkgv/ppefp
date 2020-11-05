package com.gustavvy.ppefp.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.gustavvy.ppefp.enums.Granularity;
import com.gustavvy.ppefp.enums.Period;
import com.gustavvy.ppefp.model.Candlestick;
import com.gustavvy.ppefp.model.Segment;
import com.gustavvy.ppefp.util.HttpUtil;

import java.util.Iterator;

/**
 * YahooHistoricalDataService.java
 *
 * @author Gustav V. Y
 */
public class YahooHistoricalDataService implements HistoricalDataService {

	private static final String BASE_URL = "https://query1.finance.yahoo.com/v8/finance/chart/%s?interval=%s&range=%s";

	@Override
	public Segment fetch(String ticker, Period period, Granularity granularity) {
		// Retrieve raw JSON string
		var url = BASE_URL.formatted(ticker, granularity.toString(), period.toString());
		var json = HttpUtil.get(url);

		var data = JsonParser.parseString(json)
				.getAsJsonObject()
				.getAsJsonObject("chart")
				.getAsJsonArray("result")
				.get(0)
				.getAsJsonObject();

		var prices = data.getAsJsonObject("indicators")
				.getAsJsonArray("quote")
				.get(0)
				.getAsJsonObject();

		var lows = prices.getAsJsonArray("low");
		var highs = prices.getAsJsonArray("high");
		var opens = prices.getAsJsonArray("open");
		var closes = prices.getAsJsonArray("close");
		var timestamps = data.getAsJsonArray("timestamp");

		var candlesticks = new Candlestick[timestamps.size()];

		var k = 0;
		for (Iterator<JsonElement> iterator = timestamps.iterator(); iterator.hasNext(); k++) {
			JsonElement timestamp = iterator.next();

			var high = highs.get(k).getAsFloat();
			var low = lows.get(k).getAsFloat();
			var open = opens.get(k).getAsFloat();
			var close = closes.get(k).getAsFloat();

			candlesticks[k] = new Candlestick(timestamp.getAsLong(), high, low, open, close);
		}

		return new Segment(candlesticks);
	}
}
