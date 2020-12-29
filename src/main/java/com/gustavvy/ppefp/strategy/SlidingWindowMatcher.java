package com.gustavvy.ppefp.strategy;

import com.gustavvy.ppefp.model.Segment;

/**
 * Strategy.java
 *
 * @author Gustav V. Y
 */
public record SlidingWindowMatcher(Normalizer normalizer,
								   SimilarityJudge similarityJudge) implements PatternMatcher {

	@Override
	public Segment match(Segment dataset, Segment pattern) {
		// Normalize pattern before searching
		var normPattern = normalizer.normalize(pattern);

		// Keep track of best match via min error
		var minError = Double.MAX_VALUE;
		Segment bestMatch = null;

		for (int i = 0; i < dataset.length() - pattern.length(); i++) {
			// Extract current sliding window
			var window = dataset.range(i, pattern.length());

			// Disregard if needle intersects with current sliding window
			if (window.intersects(pattern) || pattern.intersects(window)) {
				continue;
			}

			// Compute error between current window and pattern
			var error = similarityJudge.error(normalizer.normalize(window), normPattern);

			// Update error and match if necessary
			if (error < minError && error > 0) {
				minError = error;
				bestMatch = window;
			}
		}

		return bestMatch;
	}
}
