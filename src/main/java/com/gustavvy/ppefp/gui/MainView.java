package com.gustavvy.ppefp.gui;

import com.gustavvy.ppefp.EngineConfig;
import com.gustavvy.ppefp.model.Interval;
import com.gustavvy.ppefp.model.Segment;

import javax.swing.*;
import java.awt.*;

/**
 * MainView.java
 *
 * @author Gustav V. Y
 */
public class MainView extends JFrame {

	public MainView(EngineConfig config) {
		setTitle("ppefp");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		var dataset = config.getDataService().fetch(config.getTicker(), config.getPeriod(), config.getGranularity());
		addChart("AAPL (" + config.getPeriod() + " w/" + config.getGranularity() + ")", dataset, BorderLayout.WEST);

		var pattern = dataset.range(Interval.PAST_7_UNITS);
		addChart("AAPL (Last 7 days)", pattern, BorderLayout.CENTER);

		var match = config.getPatternMatcher().match(dataset, pattern);
		addChart("Best Match", match, BorderLayout.EAST);

		pack();
		setLocationRelativeTo(null);
	}

	private void addChart(String title, Segment dataset, String location) {
		add(new CandlestickChart(title, dataset), location);
	}
}
