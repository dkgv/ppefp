package com.gustavvy.ppefp.strategy;

import com.gustavvy.ppefp.model.Segment;

/**
 * PatternMatcher.java
 *
 * @author Gustav V. Y.
 */
public interface PatternMatcher {
	Segment match(Segment dataset, Segment pattern);
}
