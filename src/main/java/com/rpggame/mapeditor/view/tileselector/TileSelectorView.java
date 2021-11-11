package com.rpggame.mapeditor.view.tileselector;

import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_HEIGHT;
import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_WIDTH;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;

import com.rpggame.mapeditor.constants.MapEditorConstants;
import com.rpggame.mapeditor.controller.spritesheet.SpriteSheet;
import com.rpggame.mapeditor.model.Tile;
import com.rpggame.mapeditor.model.TileSelector;

public class TileSelectorView extends JPanel {

	private static final long serialVersionUID = 1L;

	public TileSelectorView(TileSelector tileSelector, SpriteSheet spriteSheet) {
		System.out.println("building TileSelector = "+FRAME_WIDTH);
		int selectorViewWidth = FRAME_WIDTH / 5;
		this.setPreferredSize(new Dimension(selectorViewWidth, FRAME_HEIGHT));
		this.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, 25, 15),
				BorderFactory.createMatteBorder(1, 1, 1, 1, MapEditorConstants.DARK_BACKGROUND)));

		this.setLayout(new BorderLayout());

		this.add(new TileSelectionHeader(), BorderLayout.NORTH);

		JScrollPane scroll = new JScrollPane(new TileSelectorGrid(selectorViewWidth, tileSelector, spriteSheet));
		this.setPreferredSize(new Dimension(FRAME_WIDTH / 5, FRAME_HEIGHT));
		this.add(scroll, BorderLayout.CENTER);
	}
}
