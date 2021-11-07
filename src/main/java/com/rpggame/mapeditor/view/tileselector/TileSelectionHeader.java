package com.rpggame.mapeditor.view.tileselector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TileSelectionHeader extends JPanel {

	private static final long serialVersionUID = 1L;

	public TileSelectionHeader() {
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JLabel selectorLabel = new JLabel("Tile Selection");
		this.add(selectorLabel);
	}

}
