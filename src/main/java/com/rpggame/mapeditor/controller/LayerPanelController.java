package com.rpggame.mapeditor.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import com.rpggame.mapeditor.model.tile.TileMap;
import com.rpggame.mapeditor.view.layerview.LayerPanelSettings;

public class LayerPanelController {

	private LayerPanelSettings panelSettings;
	private List<TileMap> loadedMapTiles;

	public LayerPanelController(LayerPanelSettings panelSettings, List<TileMap> loadedMapTiles) {
		this.panelSettings = panelSettings;
		this.loadedMapTiles = loadedMapTiles;
	}

	public void onItemSelected(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			this.panelSettings.updatePanelContents(loadedMapTiles.get(e.getLastIndex()).getName());
		}
	}

	public void visibilityButtonClick(ActionEvent e) {
		System.out.println("test");
	}
}
