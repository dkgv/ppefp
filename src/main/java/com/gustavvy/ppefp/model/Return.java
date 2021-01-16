package com.gustavvy.ppefp.model;

/**
 * Return.java
 *
 * @author Gustav V. Y
 */
public record Return(double percentage, double exact) {

	@Override
	public String toString() {
		return "Return{" +
				"percentage=" + percentage +
				", exact=" + exact +
				'}';
	}
}
