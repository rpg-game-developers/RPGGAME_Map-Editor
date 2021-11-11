package com.rpggame.mapeditor.controller;

import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.tile.TileMap;
import com.rpggame.mapeditor.view.layerview.LayerPanelSettings;

public class LayerPanelController {

	private LayerPanelSettings panelSettings;
	private Selector<TileMap> tileMapSelector;

	public LayerPanelController(LayerPanelSettings panelSettings, Selector<TileMap> tileMapSelector) {
		this.panelSettings = panelSettings;
		this.tileMapSelector = tileMapSelector;
	}

	public void onItemSelected(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			Object source = e.getSource();
			if (! (source instanceof JList<?> ) )
				return; // TODO: THROW EXCEPTION
			JList<?> jList = (JList<?>) source;
			int index = jList.getSelectedIndex();

			tileMapSelector.setSelected(tileMapSelector.getList().get(index));
			this.panelSettings.updatePanelContents(tileMapSelector.getList().get(index).getName());
		}
	}

	public void visibilityButtonClick(ActionEvent e) {
		System.out.println("test");
	}
}
