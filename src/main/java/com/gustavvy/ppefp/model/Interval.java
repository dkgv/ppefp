package com.gustavvy.ppefp.model;

/**
 * Interval.java
 *
 * @author Gustav V. Y
 */
public record Interval(int start, int length, boolean fromEnd) {

	public static final Interval PAST_7_UNITS = pastUnits(7);
	public static final Interval PAST_30_DAYS = pastUnits(30);

	public static Interval pastUnits(int n) {
		return new Interval(n, n, true);
	}
}
