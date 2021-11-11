package com.rpggame.mapeditor.view.layerview;

import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_HEIGHT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.rpggame.mapeditor.constants.FrameVariables;
import com.rpggame.mapeditor.constants.MapEditorConstants;
import com.rpggame.mapeditor.controller.LayerPanelController;
import com.rpggame.mapeditor.model.tile.TileMap;

public class LayerPanelView extends JPanel {

	public LayerPanelView(List<TileMap> tileMapList) {

		this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
		this.setPreferredSize(
				new Dimension(FrameVariables.FRAME_WIDTH / MapEditorConstants.PANEL_WIDTH_DIVIDER, (FRAME_HEIGHT)));

		this.setLayout(new BorderLayout());

		this.add(new LayerPanelHeader(), BorderLayout.NORTH);

		LayerPanelSettings layerPanelSettings = new LayerPanelSettings();
		LayerPanelController layerPanelController = new LayerPanelController(layerPanelSettings, tileMapList);
		this.add(new LayerPanelList(layerPanelController, tileMapList), BorderLayout.CENTER);
		this.add(layerPanelSettings, BorderLayout.SOUTH);

	}

	@Override
	public Dimension getPreferredSize() {
		System.out.println(FrameVariables.FRAME_WIDTH / MapEditorConstants.PANEL_WIDTH_DIVIDER);
		return new Dimension(FrameVariables.FRAME_WIDTH / MapEditorConstants.PANEL_WIDTH_DIVIDER, FRAME_HEIGHT);
	}
}
