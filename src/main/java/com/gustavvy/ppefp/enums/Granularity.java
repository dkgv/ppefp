package com.gustavvy.ppefp.enums;

/**
 * Interval.java
 *
 * @author Gustav V. Y
 */
public enum Granularity {
	_1M, _5M, _15M, _1H, _1D;

	@Override
	public String toString() {
		return name().toLowerCase().replace("_", "");
	}
}
