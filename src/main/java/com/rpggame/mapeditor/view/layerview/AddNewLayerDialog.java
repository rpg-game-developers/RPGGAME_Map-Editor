package com.rpggame.mapeditor.view.layerview;

import javax.swing.*;

public class AddNewLayerDialog extends JDialog {

	public AddNewLayerDialog(LayerPanelList layerPanelList) {
		this.setLocationRelativeTo(null);
		String[] layerTypes = {"Ground", "Furniture"};
		String showInputDialog = (String) JOptionPane.showInputDialog(null, "Add a new layer", "Add a new layer",
				JOptionPane.PLAIN_MESSAGE, null, layerTypes, layerTypes[0]);
		if (showInputDialog != null) {
			layerPanelList.addLayer(showInputDialog);
		}
	}

}
