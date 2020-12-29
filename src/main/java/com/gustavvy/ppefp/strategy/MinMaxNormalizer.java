package com.gustavvy.ppefp.strategy;

import com.gustavvy.ppefp.model.Candlestick;
import com.gustavvy.ppefp.model.Segment;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MinMaxNormalizer.java
 *
 * @author Gustav V. Y.
 */
public class MinMaxNormalizer implements Normalizer {

	@Override
	public Segment normalize(Segment segment) {
		var cs = segment.candlesticks();

		// Sort by ascending close price to retrieve min and max
		Arrays.sort(cs, Comparator.comparingDouble(Candlestick::close));
		var minClose = cs[0].close();
		var maxClose = cs[cs.length - 1].close();

		// Sort by timestamp to revert wrong order
		Arrays.sort(cs, Comparator.comparingLong(Candlestick::timestamp));

		var normalized = new Candlestick[cs.length];
		for (int i = 0; i < cs.length; i++) {
			var c = cs[i];

			var high = normalize(c.high(), minClose, maxClose);
			var low = normalize(c.low(), minClose, maxClose);
			var open = normalize(c.open(), minClose, maxClose);
			var close = normalize(c.close(), minClose, maxClose);

			normalized[i] = new Candlestick(c.timestamp(), high, low, open, close);
		}

		return new Segment(normalized);
	}

	private double normalize(double value, double min, double max) {
		return (value - min) / (max - min);
	}
}
