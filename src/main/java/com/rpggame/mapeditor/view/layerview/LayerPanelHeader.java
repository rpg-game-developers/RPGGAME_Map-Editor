package com.rpggame.mapeditor.view.layerview;

import javax.swing.*;

public class LayerPanelHeader extends JPanel {

	public LayerPanelHeader() {
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JLabel layerLabel = new JLabel("Layer");

		this.add(layerLabel);
	}
}
