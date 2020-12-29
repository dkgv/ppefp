package com.gustavvy.ppefp.model;

/**
 * Interval.java
 *
 * @author Gustav V. Y
 */
public record Interval(int start, int length, boolean fromEnd) {

	public static Interval pastUnits(int n) {
		return new Interval(n, n, true);
	}
}
