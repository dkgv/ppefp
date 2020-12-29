package com.gustavvy.ppefp.model;

/**
 * Segment.java
 *
 * @author Gustav V. Y
 */
public record Segment(Candlestick[] candlesticks) {

	public int length() {
		return candlesticks.length;
	}

	public Candlestick start() {
		return candlesticks[0];
	}

	public Candlestick end() {
		return candlesticks[candlesticks.length - 1];
	}

	public long duration() {
		return end().timestamp() - start().timestamp();
	}

	public boolean intersects(Segment other) {
		var otherStart = other.start().timestamp();
		var startIntersects = otherStart >= start().timestamp() && otherStart <= end().timestamp();

		var otherEnd = other.end().timestamp();
		var endIntersects = otherEnd >= start().timestamp() && otherEnd <= end().timestamp();

		return startIntersects || endIntersects;
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
}
