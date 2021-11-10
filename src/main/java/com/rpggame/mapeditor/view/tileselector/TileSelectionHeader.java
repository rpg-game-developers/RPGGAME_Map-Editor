package com.rpggame.mapeditor.view.tileselector;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rpggame.mapeditor.constants.MapEditorConstants;
import com.rpggame.mapeditor.view.utils.SmallCloseButton;

public class TileSelectionHeader extends JPanel {

	private static final long serialVersionUID = 1L;

	public TileSelectionHeader() {
		this.setBackground(MapEditorConstants.DARK_BACKGROUND);
		this.setLayout(new BorderLayout());

		// Label
		JLabel selectorLabel = new JLabel("Tile Selection");
		selectorLabel.setForeground(Color.white);
		selectorLabel.setFont(MapEditorConstants.REGULAR_FONT);
		selectorLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

		this.add(selectorLabel, BorderLayout.WEST);

		this.add(new SmallCloseButton(), BorderLayout.EAST);

	}

}
