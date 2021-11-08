package com.rpggame.mapeditor.view.tileselector;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.rpggame.mapeditor.model.MapTile;

public class TileSelectorView extends JPanel {

	private static final long serialVersionUID = 1L;

	public TileSelectorView(List<MapTile> tileList) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int selectorViewWidth = screenSize.width / 5;
		this.setPreferredSize(new Dimension(selectorViewWidth, screenSize.height));
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.add(new TileSelectionHeader());

		JScrollPane scroll = new JScrollPane(new TileSelectorGrid(selectorViewWidth, tileList));
		this.setPreferredSize(new Dimension(screenSize.width / 5, screenSize.height));
		this.add(scroll);

	}
}
