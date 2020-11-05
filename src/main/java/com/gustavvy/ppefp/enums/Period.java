package com.gustavvy.ppefp.enums;

/**
 * Period.java
 *
 * @author Gustav V. Y
 */
public enum Period {
	_1D, _5D, _1MO, _6M, YTD, _1Y, _5Y, MAX;

	@Override
	public String toString() {
		return name().toLowerCase().replace("_", "");
	}
}
