package com.rpggame.mapeditor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rpggame.mapeditor.model.MapTile;
import com.rpggame.mapeditor.view.layerview.LayerPanelView;
import com.rpggame.mapeditor.view.tileselector.TileSelectorView;

public class MapEditorWindow {

	private List<MapTile> tileList;

	public MapEditorWindow() {
		buildAndShowView();
	}

	public void buildAndShowView() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);

		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());

		// TODO outsource tileList creation
		this.tileList = new ArrayList<>();
	
		this.tileList.add(new MapTile("Background", "Water", 0, "imgPath"));
		this.tileList.add(new MapTile("Forground", "Grass", 1, "imgPath"));
		this.tileList.add(new MapTile("Forground", "Dirt", 1, "imgPath"));

		root.add(new TileSelectorView(this.tileList), BorderLayout.EAST);
		root.add(new MapEditingPanel(), BorderLayout.CENTER);
		root.add(new LayerPanelView(tileList), BorderLayout.WEST);

		frame.add(root);
		frame.setVisible(true);
	}

}
