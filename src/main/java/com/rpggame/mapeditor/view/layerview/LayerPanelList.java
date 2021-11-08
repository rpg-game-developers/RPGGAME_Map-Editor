package com.rpggame.mapeditor.view.layerview;

import com.rpggame.mapeditor.controller.LayerPanelController;

import javax.swing.*;
import java.awt.*;

public class LayerPanelList extends JPanel {

	public LayerPanelList(LayerPanelController layerPanelController) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		String[] layerText = {"test", "test1", "test2", "test3"};

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		JList<String> layers = new JList<>(layerText);
		layers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		layers.setLayoutOrientation(JList.VERTICAL);
		layers.setVisibleRowCount(-1);
		layers.addListSelectionListener(layerPanelController::onItemSelected);

		JScrollPane layersScroller = new JScrollPane(layers);
		layersScroller.setPreferredSize(new Dimension(screenSize.width/5, screenSize.height));
		this.add(layersScroller);
	}

}
