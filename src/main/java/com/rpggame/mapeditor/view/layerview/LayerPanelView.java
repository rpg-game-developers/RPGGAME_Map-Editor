package com.rpggame.mapeditor.view.layerview;

import com.rpggame.mapeditor.controller.LayerPanelController;

import javax.swing.*;
import java.awt.*;

public class LayerPanelView extends JPanel {

	public LayerPanelView() {
		LayerPanelController layerPanelController = new LayerPanelController();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(screenSize.width/5, screenSize.height));

		this.setLayout(new BorderLayout());

		this.add(new LayerPanelHeader(), BorderLayout.NORTH);
		this.add(new LayerPanelList(layerPanelController), BorderLayout.CENTER);
		this.add(new LayerPanelSettings(), BorderLayout.SOUTH);

	}
}
