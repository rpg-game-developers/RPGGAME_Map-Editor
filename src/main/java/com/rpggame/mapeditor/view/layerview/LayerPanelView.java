package com.rpggame.mapeditor.view.layerview;

import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_HEIGHT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.rpggame.mapeditor.constants.FrameVariables;
import com.rpggame.mapeditor.constants.MapEditorConstants;
import com.rpggame.mapeditor.controller.LayerPanelController;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.tile.TileMap;

public class LayerPanelView extends JPanel {

	public LayerPanelView(Selector<TileMap> tileMapSelector) {

		this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
		this.setPreferredSize(
				new Dimension(FrameVariables.FRAME_WIDTH / MapEditorConstants.PANEL_WIDTH_DIVIDER, (FRAME_HEIGHT)));

		this.setLayout(new BorderLayout());

		this.add(new LayerPanelHeader(), BorderLayout.NORTH);

		LayerPanelSettings layerPanelSettings = new LayerPanelSettings();
		LayerPanelController layerPanelController = new LayerPanelController(layerPanelSettings, tileMapSelector);
		this.add(new LayerPanelList(layerPanelController, tileMapSelector), BorderLayout.CENTER);
		this.add(layerPanelSettings, BorderLayout.SOUTH);

	}

	@Override
	public Dimension getPreferredSize() {
		System.out.println(FrameVariables.FRAME_WIDTH / MapEditorConstants.PANEL_WIDTH_DIVIDER);
		return new Dimension(FrameVariables.FRAME_WIDTH / MapEditorConstants.PANEL_WIDTH_DIVIDER, FRAME_HEIGHT);
	}
}
