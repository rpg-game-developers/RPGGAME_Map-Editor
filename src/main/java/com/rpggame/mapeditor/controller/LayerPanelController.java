package com.rpggame.mapeditor.controller;

import java.util.List;

import javax.swing.event.ListSelectionEvent;

import com.rpggame.mapeditor.model.MapTile;
import com.rpggame.mapeditor.view.layerview.LayerPanelSettings;

public class LayerPanelController {

	private LayerPanelSettings panelSettings;
	private List<MapTile> loadedMapTiles;

	public LayerPanelController(LayerPanelSettings panelSettings, List<MapTile> loadedMapTiles) {
		this.panelSettings = panelSettings;
		this.loadedMapTiles = loadedMapTiles;
	}

	public void onItemSelected(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			this.panelSettings.updatePanelContents(loadedMapTiles.get(e.getLastIndex()).getTileName());
		}
	}
}
