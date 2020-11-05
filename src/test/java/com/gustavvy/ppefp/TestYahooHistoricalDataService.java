package com.gustavvy.ppefp;

import com.gustavvy.ppefp.enums.Granularity;
import com.gustavvy.ppefp.enums.Period;
import com.gustavvy.ppefp.service.HistoricalDataService;
import com.gustavvy.ppefp.service.YahooHistoricalDataService;
import org.junit.Assert;
import org.junit.Test;

/**
 * TestYahooHistoricalDataService.java
 *
 * @author Gustav V. Y
 */
public class TestYahooHistoricalDataService {

	private static final String TICKER = "AAPL";
	private final HistoricalDataService historicalDataService = new YahooHistoricalDataService();

	@Test
	public void testFiveDayPeriodOneDayGranularity() {
		var data = historicalDataService.fetch(TICKER, Period._5D, Granularity._1D);
		Assert.assertEquals(5, data.length());
	}

	@Test
	public void testFiveDayPeriodOneHourGranularity() {
		var data = historicalDataService.fetch(TICKER, Period._5D, Granularity._1H);
		Assert.assertEquals(5 * 7, data.length());
	}

	@Test
	public void testOneDayPeriodOneHourGranularity() {
		var data = historicalDataService.fetch(TICKER, Period._1D, Granularity._1H);
		Assert.assertEquals(7, data.length());
	}
}
