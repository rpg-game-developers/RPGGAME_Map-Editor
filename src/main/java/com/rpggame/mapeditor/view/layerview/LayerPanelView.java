package com.rpggame.mapeditor.view.layerview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.rpggame.mapeditor.controller.LayerPanelController;

public class LayerPanelView extends JPanel {

	public LayerPanelView() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(screenSize.width / 5, screenSize.height));

		this.setLayout(new BorderLayout());

		this.add(new LayerPanelHeader(), BorderLayout.NORTH);

		LayerPanelSettings layerPanelSettings = new LayerPanelSettings();
		LayerPanelController layerPanelController = new LayerPanelController(layerPanelSettings);
		this.add(new LayerPanelList(layerPanelController), BorderLayout.CENTER);
		this.add(layerPanelSettings, BorderLayout.SOUTH);

	}
}
