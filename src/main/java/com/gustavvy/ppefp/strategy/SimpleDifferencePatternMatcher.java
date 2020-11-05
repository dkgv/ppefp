package com.gustavvy.ppefp.strategy;

import com.gustavvy.ppefp.model.Segment;

/**
 * PatternMatchingStrategy.java
 *
 * @author Gustav V. Y
 */
public class SimpleDifferencePatternMatcher implements PatternMatcher {

	@Override
	public Segment match(Segment dataset, Segment pattern) {
		pattern = pattern.normalize();

		Segment bestMatch = null;
		var minError = Float.MAX_VALUE;

		for (int i = 0; i < dataset.length() - pattern.length(); i++) {
			var subDataset = dataset.range(i, pattern.length());
			var normalizedSubDataset = subDataset.normalize();
			var error = pointByPointError(normalizedSubDataset, pattern);
			if (error < minError && error > 0) {
				minError = error;
				bestMatch = subDataset;
			}
		}

		return bestMatch;
	}

	private float pointByPointError(Segment dataset, Segment pattern) {
		var difference = 0f;
		for (int i = 0; i < dataset.length(); i++) {
			var dataValue = dataset.candlesticks()[i].close();
			var patternValue = pattern.candlesticks()[i].close();
			difference += Math.abs(dataValue - patternValue);
		}
		return difference;
	}
}
