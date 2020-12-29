package com.gustavvy.ppefp.strategy;

import com.gustavvy.ppefp.model.Segment;

/**
 * PatternMatchingStrategy.java
 *
 * @author Gustav V. Y
 */
public class PairwiseSimilarityJudge implements SimilarityJudge {

	@Override
	public double error(Segment dataset, Segment pattern) {
		var diff = 0f;
		for (int i = 0; i < dataset.length(); i++) {
			var dc = dataset.candlesticks()[i];
			var pc = pattern.candlesticks()[i];

			diff += Math.abs(dc.high() - pc.high());
			diff += Math.abs(dc.low() - pc.low());
			diff += Math.abs(dc.open() - pc.open());
			diff += Math.abs(dc.close() - pc.close());
		}
		return diff;
	}
}
