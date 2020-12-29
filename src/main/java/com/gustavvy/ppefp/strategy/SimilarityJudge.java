package com.gustavvy.ppefp.strategy;

import com.gustavvy.ppefp.model.Segment;

/**
 * Comparer.java
 *
 * @author Gustav V. Y.
 */
public interface SimilarityJudge {
	double error(Segment dataset, Segment pattern);
}
