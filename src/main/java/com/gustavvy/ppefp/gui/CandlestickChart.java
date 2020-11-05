package com.gustavvy.ppefp.gui;

import com.gustavvy.ppefp.model.Segment;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.DefaultHighLowDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * CandlestickChart.java
 *
 * @author Gustav V. Y
 */
public class CandlestickChart extends JPanel {

	public CandlestickChart(String title, Segment segment) {
		setMaximumSize(new Dimension(200, 200));
		var chart = ChartFactory.createCandlestickChart(title, "Date", "Price", transform(segment), false);
		var panel = new ChartPanel(chart);
		add(panel);
	}

	private DefaultHighLowDataset transform(Segment segment) {
		var n = segment.candlesticks().length;

		var dates = new Date[n];
		var highs = new double[n];
		var lows = new double[n];
		var opens = new double[n];
		var closes = new double[n];

		for (int i = 0; i < n; i++) {
			var c = segment.candlesticks()[i];
			dates[i] = new Date(c.timestamp() * 1000);
			highs[i] = c.high();
			lows[i] = c.low();
			opens[i] = c.open();
			closes[i] = c.close();
		}

		return new DefaultHighLowDataset("", dates, highs, lows, opens, closes, new double[n]);
	}
}
