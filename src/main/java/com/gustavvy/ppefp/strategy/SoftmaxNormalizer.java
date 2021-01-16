package com.gustavvy.ppefp.strategy;

import com.gustavvy.ppefp.model.Candlestick;
import com.gustavvy.ppefp.model.Segment;

/**
 * SoftmaxNormalizer.java
 *
 * @author Gustav V. Y.
 */
public class SoftmaxNormalizer implements Normalizer {

	@Override
	public Segment normalize(Segment segment) {
		var cs = segment.candlesticks();

		// s(x_i) = e^x_i / sum(e^x_j) from j=1 to n
		double closeSum = 0, openSum = 0, highSum = 0, lowSum = 0;
		for (Candlestick c : cs) {
			closeSum += Math.exp(c.close());
			openSum += Math.exp(c.open());
			highSum += Math.exp(c.high());
			lowSum += Math.exp(c.low());
		}

		var normalized = new Candlestick[cs.length];
		for (int i = 0; i < cs.length; i++) {
			var c = cs[i];

			var high = Math.exp(c.high()) / highSum;
			var low = Math.exp(c.low()) / lowSum;
			var open = Math.exp(c.open()) / openSum;
			var close = Math.exp(c.close()) / closeSum;

			normalized[i] = new Candlestick(c.timestamp(), high, low, open, close);
		}

		return new Segment(normalized);
	}
}
