package com.rpggame.mapeditor.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import com.google.gson.Gson;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.tile.TileMap;
import com.rpggame.mapeditor.model.tile.TileMapJson;
import com.rpggame.mapeditor.view.history.HistoryView;
import com.rpggame.mapeditor.view.tileselector.TileSelectorView;
import com.rpggame.mapeditor.view.topbar.AboutDialog;
import com.rpggame.mapeditor.view.topbar.WindowSizeChangeDialog;

public class TopBarController {

	private final TileSelectorView tileSelectorView;
	private final HistoryView historyView;
	private final Consumer<String> updateWindowSize;
	private final Selector<TileMap> tileMapSelector;

	public TopBarController(Consumer<String> updateWindowSize, TileSelectorView tileSelectorView, HistoryView historyView, Selector<TileMap> tileMapSelector) {
		this.updateWindowSize = updateWindowSize;
		this.tileSelectorView = tileSelectorView;
		this.historyView = historyView;
		this.tileMapSelector = tileMapSelector;
	}

	public void newItemSelected(ActionEvent e) {

	}

	public void saveItemSelected(ActionEvent e) {
		TileMapJson[] tileMaps = tileMapSelector.getList().stream().map(TileMapJson::new).toArray(TileMapJson[]::new);

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
			tileMapSelector.setList(Arrays.stream(tileMaps).map(TileMap::new).collect(Collectors.toList()));
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

	public void openWindowSizeDialog(ActionEvent e) {
		new WindowSizeChangeDialog(this.updateWindowSize);
	}

	public void aboutItemSelected(ActionEvent e) {
		new AboutDialog();
	}

	public void showTileSelectionItemSelected() {
		if(!tileSelectorView.isVisible()) {
			tileSelectorView.setVisible(true);
		}
	}

	public void showHistorySelected() {
		if(!historyView.isVisible()) {
			historyView.setVisible(true);
		}
	}
}
