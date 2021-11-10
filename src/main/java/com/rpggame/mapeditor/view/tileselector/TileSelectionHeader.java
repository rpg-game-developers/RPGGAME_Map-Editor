package com.rpggame.mapeditor.view.tileselector;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rpggame.mapeditor.constants.FrameVariables;
import com.rpggame.mapeditor.constants.MapEditorConstants;

public class TileSelectionHeader extends JPanel {

	private static final long serialVersionUID = 1L;

	public TileSelectionHeader() {
		this.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
		this.setBackground(MapEditorConstants.PRIMARY);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(FrameVariables.FRAME_WIDTH / 5, 100));

		// Label
		JLabel selectorLabel = new JLabel("Tile Selection");
		Font font = new Font("Comic Sans", Font.BOLD, 20);
		selectorLabel.setForeground(Color.white);
		selectorLabel.setFont(font);

		this.add(selectorLabel);

		// Close button

		JButton closeButton = new JButton("x");
		closeButton.setForeground(MapEditorConstants.LIGHT_GRAY);
		closeButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		closeButton.setBackground(MapEditorConstants.TRANSPARENT);
		
		this.add(closeButton);

	}

}
