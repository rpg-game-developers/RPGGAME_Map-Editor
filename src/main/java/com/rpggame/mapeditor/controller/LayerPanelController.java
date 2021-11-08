package com.rpggame.mapeditor.controller;

import javax.swing.event.ListSelectionEvent;

import com.rpggame.mapeditor.view.layerview.LayerPanelSettings;

public class LayerPanelController {

	private LayerPanelSettings panelSettings;

	public LayerPanelController(LayerPanelSettings panelSettings) {
		this.panelSettings = panelSettings;
	}

	public void onItemSelected(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			this.panelSettings.updatePanelContents("test");
		}
	}
}
