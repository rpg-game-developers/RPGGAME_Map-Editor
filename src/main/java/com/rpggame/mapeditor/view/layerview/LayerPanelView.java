package com.rpggame.mapeditor.view.layerview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.rpggame.mapeditor.controller.LayerPanelController;
import com.rpggame.mapeditor.model.MapTile;

public class LayerPanelView extends JPanel {

	public LayerPanelView(List<MapTile> tileList) {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(screenSize.width / 5, screenSize.height));

		this.setLayout(new BorderLayout());

		this.add(new LayerPanelHeader(), BorderLayout.NORTH);

		LayerPanelSettings layerPanelSettings = new LayerPanelSettings();
		LayerPanelController layerPanelController = new LayerPanelController(layerPanelSettings, tileList);
		this.add(new LayerPanelList(layerPanelController, tileList), BorderLayout.CENTER);
		this.add(layerPanelSettings, BorderLayout.SOUTH);

	}
}
