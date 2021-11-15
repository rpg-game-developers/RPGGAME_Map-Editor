package com.rpggame.mapeditor.controller;

import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.tile.TileMap;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;

/**
 * Responsible for backend of LayerView and all the components inside it.
 */
public class LayerPanelController {

	private final Selector<TileMap> tileMapSelector;

	/**
	 * Creates a new instance of the controller.
	 * @param tileMapSelector the tileMapSelector.
	 */
	public LayerPanelController(Selector<TileMap> tileMapSelector) {
		this.tileMapSelector = tileMapSelector;
	}

	/**
	 * Triggers whenever an item in the LayerPanelList is selected.
	 * @param e ListSelectionEvent.
	 */
	public void onItemSelected(@NotNull ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			Object source = e.getSource();
			if (!(source instanceof JList<?> ) )
				throw new ClassCastException("Object was not of type JList");
			JList<?> jList = (JList<?>) source;
		}
	}

	/**
	 * Changes the visibility of the layer and changes the visibility icon.
	 * @param e ActionEvent
	 */
	public void visibilityButtonClick(ActionEvent e) {
		// TODO: show and hide layers
		System.out.println("test");
	}
}
