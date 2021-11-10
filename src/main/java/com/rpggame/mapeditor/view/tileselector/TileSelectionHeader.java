package com.rpggame.mapeditor.view.tileselector;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TileSelectionHeader extends JPanel {

	private static final long serialVersionUID = 1L;

	public TileSelectionHeader() {
		this.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
		JLabel selectorLabel = new JLabel("Tile Selection");
		Font font = new Font("Comic Sans", Font.BOLD, 20);
		selectorLabel.setFont(font);
		this.add(selectorLabel);
	}

}
