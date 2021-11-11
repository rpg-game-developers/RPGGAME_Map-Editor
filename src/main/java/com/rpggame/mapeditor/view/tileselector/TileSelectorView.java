package com.rpggame.mapeditor.view.tileselector;

import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_HEIGHT;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;

import com.rpggame.mapeditor.constants.FrameVariables;
import com.rpggame.mapeditor.constants.MapEditorConstants;
import com.rpggame.mapeditor.controller.spritesheet.SpriteSheet;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.tile.TileMap;
import com.rpggame.mapeditor.model.tile.TileSelector;

public class TileSelectorView extends JPanel {

	private static final long serialVersionUID = 1L;

	public TileSelectorView(TileSelector tileSelector, Selector<TileMap> tileMapSelector) {
		int selectorViewWidth = FrameVariables.FRAME_WIDTH / MapEditorConstants.PANEL_WIDTH_DIVIDER;
		this.setPreferredSize(new Dimension(selectorViewWidth, FRAME_HEIGHT));
		this.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, 25, 15),
				BorderFactory.createMatteBorder(1, 1, 1, 1, MapEditorConstants.DARK_BACKGROUND)));

		this.setLayout(new BorderLayout());

		this.add(new TileSelectionHeader(), BorderLayout.NORTH);

		JScrollPane scroll = new JScrollPane(new TileSelectorGrid(selectorViewWidth, tileSelector, tileMapSelector));
		this.setPreferredSize(new Dimension(selectorViewWidth, FRAME_HEIGHT));
		this.add(scroll, BorderLayout.CENTER);
	}

	@Override
	public Dimension getPreferredSize() {
		System.out.println(FrameVariables.FRAME_WIDTH / MapEditorConstants.PANEL_WIDTH_DIVIDER);
		return new Dimension(FrameVariables.FRAME_WIDTH/MapEditorConstants.PANEL_WIDTH_DIVIDER, FRAME_HEIGHT);
	}

}
