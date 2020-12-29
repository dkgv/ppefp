package com.gustavvy.ppefp;

import com.gustavvy.ppefp.enums.Granularity;
import com.gustavvy.ppefp.enums.Period;
import com.gustavvy.ppefp.gui.MainView;
import com.gustavvy.ppefp.model.Interval;
import com.gustavvy.ppefp.service.YahooHistoricalDataService;
import com.gustavvy.ppefp.strategy.MinMaxNormalizer;
import com.gustavvy.ppefp.strategy.PairwiseSimilarityJudge;
import com.gustavvy.ppefp.strategy.SlidingWindowMatcher;

import javax.swing.*;

/**
 * Boot.java
 *
 * @author Gustav V. Y
 */
public class Boot {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			var patternMatcher = new SlidingWindowMatcher(
					new MinMaxNormalizer(),
					new PairwiseSimilarityJudge()
			);

			var config = EngineConfig.builder()
					.setTicker("AAPL")
					.setNeedleInterval(Interval.pastUnits(7))
					.setGranularity(Granularity._1D)
					.setPeriod(Period.YTD)
					.setPatternMatcher(patternMatcher)
					.setDataService(new YahooHistoricalDataService())
					.build();

			var view = new MainView(config);
			view.setVisible(true);
		});
	}
}
