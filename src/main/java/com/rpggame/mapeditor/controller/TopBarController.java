package com.rpggame.mapeditor.controller;

import com.google.gson.Gson;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.tile.Tile;
import com.rpggame.mapeditor.model.tile.TileMap;
import com.rpggame.mapeditor.model.tile.TileMapJson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TopBarController {

	private final Consumer<String> updateWindowSize;
	private final Selector<TileMap> tileMapSelector;

	public TopBarController(Consumer<String> updateWindowSize, Selector<TileMap> tileMapSelector) {
		this.updateWindowSize = updateWindowSize;
		this.tileMapSelector = tileMapSelector;
	}

	public void newItemSelected(ActionEvent e) {

	}

	public void saveItemSelected(ActionEvent e) {
		TileMapJson[] tileMaps = new TileMapJson[1];

		Gson gson = new Gson();
		String s = gson.toJson(tileMaps);
		try {
			File directory = new File("saves/");
			if (! directory.exists()) {
				directory.mkdir();
			}

			PrintWriter file = new PrintWriter("saves/tilemap.json");
			file.println(s);
			file.close();
		} catch (IOException exception) {
			System.out.println("An error occurred.");
		}
	}

	public void loadItemSelected(ActionEvent e) {
		try {
			String jsonString = Files.readString(Path.of("saves/tilemap.json"));
			Gson gson = new Gson();
			TileMapJson[] tileMaps = gson.fromJson(jsonString, TileMapJson[].class);
		} catch (IOException exception) {
			System.out.println("An error occurred.");
		}
	}

	public void exitItemSelected(ActionEvent e) {
		JMenuItem source = (JMenuItem) e.getSource();
		JPopupMenu popupMenu = (JPopupMenu) source.getParent();
		JFrame frame = (JFrame) SwingUtilities.getRoot(popupMenu.getInvoker());
		frame.dispose();
	}

}
