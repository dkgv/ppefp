package com.gustavvy.ppefp.service;

import com.gustavvy.ppefp.enums.Granularity;
import com.gustavvy.ppefp.enums.Period;
import com.gustavvy.ppefp.model.Segment;

/**
 * HistoricalDataService.java
 *
 * @author Gustav V. Y
 */
public interface HistoricalDataService {

	Segment fetch(String ticker, Period period, Granularity granularity);
}
