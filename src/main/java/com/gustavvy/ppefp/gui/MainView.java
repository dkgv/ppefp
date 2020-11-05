package com.gustavvy.ppefp.gui;

import com.gustavvy.ppefp.EngineConfig;
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

		var dataset = config.dataService().fetch(config.ticker(), config.period(), config.granularity());
		addChart("Historic " + config.ticker() + " (" + config.period() + " w/" + config.granularity() + ")", dataset, BorderLayout.WEST);

		var pattern = dataset.range(config.needleInterval());
		addChart("Needle Performance", pattern, BorderLayout.CENTER);

		var match = config.patternMatcher().match(dataset, pattern);
		addChart("Best Match", match, BorderLayout.EAST);

		pack();
		setLocationRelativeTo(null);
	}

	private void addChart(String title, Segment dataset, String location) {
		add(new CandlestickChart(title, dataset), location);
	}
}
