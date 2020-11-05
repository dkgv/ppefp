package com.gustavvy.ppefp;

import com.gustavvy.ppefp.enums.Granularity;
import com.gustavvy.ppefp.enums.Period;
import com.gustavvy.ppefp.model.Interval;
import com.gustavvy.ppefp.service.HistoricalDataService;
import com.gustavvy.ppefp.strategy.PatternMatcher;

/**
 * EngineConfig.java
 *
 * @author Gustav V. Y
 */
public record EngineConfig(String ticker,
                           Period period,
                           Granularity granularity,
                           Interval needleInterval,
                           PatternMatcher patternMatcher,
                           HistoricalDataService dataService) {

	public static EngineConfigBuilder builder() {
		return new EngineConfigBuilder();
	}

	public static class EngineConfigBuilder {

		private String ticker;
		private Interval needleInterval;
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

		public EngineConfigBuilder setNeedleInterval(Interval needleInterval) {
			this.needleInterval = needleInterval;
			return this;
		}

		public EngineConfig build() {
			return new EngineConfig(ticker, period, granularity, needleInterval, patternMatcher, dataService);
		}
	}
}
