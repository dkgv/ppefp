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
		setLayout(new GridLayout(3, 1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		var dataset = config.dataService().fetch(config.ticker(), config.period(), config.granularity());
		addChart("Historic " + config.ticker() + " (" + config.period() + " w/" + config.granularity() + ")", dataset);

		var pattern = dataset.range(config.needleInterval());
		addChart("Needle Performance", pattern);

		var match = config.patternMatcher().match(dataset, pattern);
		addChart("Best Match", match);

		pack();
		setLocationRelativeTo(null);
	}

	private void addChart(String title, Segment dataset) {
		add(new CandlestickChart(title, dataset));
	}
}
