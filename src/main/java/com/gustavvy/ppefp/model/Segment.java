package com.gustavvy.ppefp.model;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Segment.java
 *
 * @author Gustav V. Y
 */
public record Segment(Candlestick[] candlesticks) {

	public int length() {
		return candlesticks.length;
	}

	public Segment range(Interval interval) {
		return range(interval.fromEnd() ? length() - interval.start() : interval.start(), interval.length());
	}

	public Segment range(int start, int length) {
		if (length < 0) {
			throw new IllegalArgumentException("Length must be >=0");
		}
		var range = new Candlestick[length];
		System.arraycopy(candlesticks, start, range, 0, length);
		return new Segment(range);
	}

	public Segment normalize() {
		// Sort by ascending close price to retrieve min and max
		Arrays.sort(candlesticks, Comparator.comparingDouble(Candlestick::close));
		var minClose = candlesticks[0].close();
		var maxClose = candlesticks[candlesticks.length - 1].close();

		// Sort by timestamp to revert wrong order
		Arrays.sort(candlesticks, Comparator.comparingLong(Candlestick::timestamp));

		var normalized = new Candlestick[candlesticks.length];
		for (int i = 0; i < candlesticks.length; i++) {
			var c = candlesticks[i];

			var high = normalize(c.high(), minClose, maxClose);
			var low = normalize(c.low(), minClose, maxClose);
			var open = normalize(c.open(), minClose, maxClose);
			var close = normalize(c.close(), minClose, maxClose);

			normalized[i] = new Candlestick(c.timestamp(), high, low, open, close);
		}

		return new Segment(normalized);
	}

	private float normalize(float value, float min, float max) {
		return (value - min) / (max - min);
	}
}
