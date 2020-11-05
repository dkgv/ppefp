package com.gustavvy.ppefp;

import com.gustavvy.ppefp.enums.Granularity;
import com.gustavvy.ppefp.enums.Period;
import com.gustavvy.ppefp.service.HistoricalDataService;
import com.gustavvy.ppefp.strategy.PatternMatcher;

/**
 * EngineConfig.java
 *
 * @author Gustav V. Y
 */
public class EngineConfig {

	private final String ticker;
	private Period period;
	private Granularity granularity;
	private final PatternMatcher patternMatcher;
	private final HistoricalDataService dataService;

	private EngineConfig(String ticker, Period period, Granularity granularity, PatternMatcher patternMatcher,
	                    HistoricalDataService dataService) {
		this.ticker = ticker;
		this.period = period;
		this.granularity = granularity;
		this.patternMatcher = patternMatcher;
		this.dataService = dataService;
	}

	public Period getPeriod() {
		return period;
	}

	public Granularity getGranularity() {
		return granularity;
	}

	public String getTicker() {
		return ticker;
	}

	public PatternMatcher getPatternMatcher() {
		return patternMatcher;
	}

	public HistoricalDataService getDataService() {
		return dataService;
	}

	public static EngineConfigBuilder builder() {
		return new EngineConfigBuilder();
	}

	public static class EngineConfigBuilder {

		private String ticker;
		private Period period;
		private Granularity granularity;
		private HistoricalDataService dataService;
		private PatternMatcher patternMatcher;

		public EngineConfigBuilder setPeriod(Period period) {
			this.period = period;
			return this;
		}

		public EngineConfigBuilder setGranularity(Granularity granularity) {
			this.granularity = granularity;
			return this;
		}

		public EngineConfigBuilder setTicker(String ticker) {
			this.ticker = ticker;
			return this;
		}

		public EngineConfigBuilder setPatternMatcher(PatternMatcher patternMatcher) {
			this.patternMatcher = patternMatcher;
			return this;
		}

		public EngineConfigBuilder setDataService(HistoricalDataService dataService) {
			this.dataService = dataService;
			return this;
		}

		public EngineConfig build() {
			return new EngineConfig(ticker, period, granularity, patternMatcher, dataService);
		}
	}
}
