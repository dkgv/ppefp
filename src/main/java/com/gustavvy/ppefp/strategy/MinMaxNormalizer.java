package com.gustavvy.ppefp.strategy;

import com.gustavvy.ppefp.model.Candlestick;
import com.gustavvy.ppefp.model.Segment;

/**
 * MinMaxNormalizer.java
 *
 * @author Gustav V. Y.
 */
public class MinMaxNormalizer implements Normalizer {

	@Override
	public Segment normalize(Segment segment) {
		var cs = segment.candlesticks();

		// Retrieve min and max close prices
		var minClose = Double.MAX_VALUE;
		var maxClose = Double.MIN_VALUE;
		for (Candlestick c : cs) {
			var close = c.close();
			if (minClose > close) {
				minClose = close;
			}
			if (maxClose < close) {
				maxClose = close;
			}
		}

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
