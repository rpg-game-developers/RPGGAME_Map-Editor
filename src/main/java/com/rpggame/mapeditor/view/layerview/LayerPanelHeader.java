package com.rpggame.mapeditor.view.layerview;

import javax.swing.*;

import static com.rpggame.mapeditor.constants.MapEditorConstants.REGULAR_FONT;

public class LayerPanelHeader extends JPanel {

	public LayerPanelHeader(LayerPanelList layerPanelList) {
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JButton layerButton = new JButton("Layers");
		layerButton.setRolloverEnabled(false);
		layerButton.setFocusable(false);
		layerButton.setFont(REGULAR_FONT);
		layerButton.addActionListener(e -> new AddNewLayerDialog(layerPanelList));
		this.add(layerButton);
	}
}
