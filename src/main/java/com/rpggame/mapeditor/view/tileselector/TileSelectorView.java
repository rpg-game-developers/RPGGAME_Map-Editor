package com.rpggame.mapeditor.view.tileselector;

import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_HEIGHT;
import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_WIDTH;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.rpggame.mapeditor.controller.spritesheet.SpriteSheet;
import com.rpggame.mapeditor.model.MapTile;

public class TileSelectorView extends JPanel {

	private static final long serialVersionUID = 1L;

	public TileSelectorView(List<MapTile> tileList, SpriteSheet spriteSheet) {
		int selectorViewWidth = FRAME_WIDTH / 5;
		this.setPreferredSize(new Dimension(selectorViewWidth, FRAME_HEIGHT));
		this.setBorder(BorderFactory.createMatteBorder(0,1,1,1, Color.black));

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.add(new TileSelectionHeader());

		JScrollPane scroll = new JScrollPane(new TileSelectorGrid(selectorViewWidth, tileList, spriteSheet));
		this.setPreferredSize(new Dimension(FRAME_WIDTH / 5, FRAME_HEIGHT));
		this.add(scroll);

	}
}
