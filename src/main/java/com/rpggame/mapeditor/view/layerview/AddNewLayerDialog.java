package com.rpggame.mapeditor.view.layerview;

import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.tile.TileMap;

import static com.rpggame.mapeditor.constants.MapEditorConstants.FURNITURE;
import static com.rpggame.mapeditor.constants.MapEditorConstants.GROUND;

import javax.swing.*;

public class AddNewLayerDialog extends JDialog {

	public AddNewLayerDialog(LayerPanelList layerPanelList) {
		this.setLocationRelativeTo(null);
		String[] layerTypes = {"Ground", "Furniture"};
		String showInputDialog = (String) JOptionPane.showInputDialog(null, "Add a new layer", "Add a new layer",
				JOptionPane.PLAIN_MESSAGE, null, layerTypes, layerTypes[0]);
		if (showInputDialog != null) {
			Selector<TileMap> tileMapSelector = layerPanelList.getTileMapSelector();
			//TODO: refactor to controller.
			switch (showInputDialog.toLowerCase()) {
				case "ground":
					tileMapSelector.getList().add(GROUND);
					break;
				case "furniture":
					tileMapSelector.getList().add(FURNITURE);
					break;
			}
			tileMapSelector.setList(tileMapSelector.getList());
		}
	}

}
