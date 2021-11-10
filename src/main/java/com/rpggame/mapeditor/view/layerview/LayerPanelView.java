package com.rpggame.mapeditor.view.layerview;

import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_HEIGHT;
import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_WIDTH;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.rpggame.mapeditor.controller.LayerPanelController;
import com.rpggame.mapeditor.model.MapTile;

public class LayerPanelView extends JPanel {

	public LayerPanelView(List<MapTile> tileList) {

		this.setBorder(BorderFactory.createMatteBorder(0,1,1,1, Color.black));
		this.setPreferredSize(new Dimension(FRAME_WIDTH / 5, FRAME_HEIGHT));

		this.setLayout(new BorderLayout());

		this.add(new LayerPanelHeader(), BorderLayout.NORTH);

		LayerPanelSettings layerPanelSettings = new LayerPanelSettings();
		LayerPanelController layerPanelController = new LayerPanelController(layerPanelSettings, tileList);
		this.add(new LayerPanelList(layerPanelController, tileList), BorderLayout.CENTER);
		this.add(layerPanelSettings, BorderLayout.SOUTH);

	}
}
