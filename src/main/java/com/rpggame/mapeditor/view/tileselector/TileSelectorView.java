package com.rpggame.mapeditor.view.tileselector;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TileSelectorView extends JPanel {

	public TileSelectorView() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int selectorViewWidth = screenSize.width / 5;
		this.setPreferredSize(new Dimension(selectorViewWidth, screenSize.height));
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.add(new JLabel("Tile Selector"));

		List<Color> tileList = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 150; i++) {
			tileList.add(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		}

		JScrollPane scroll = new JScrollPane(new TileSelectorGrid(selectorViewWidth, tileList));
		this.setPreferredSize(new Dimension(screenSize.width / 5, screenSize.height));
		this.add(scroll);

	}
}
