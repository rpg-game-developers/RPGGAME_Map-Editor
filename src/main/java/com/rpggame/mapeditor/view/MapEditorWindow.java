package com.rpggame.mapeditor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rpggame.mapeditor.view.tileselector.TileSelectorView;

public class MapEditorWindow {

	private List<Color> tileList;

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
		Random random = new Random();
		for (int i = 0; i < 150; i++) {
			this.tileList.add(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		}

		root.add(new TileSelectorView(this.tileList), BorderLayout.EAST);
		root.add(new MapEditingPanel(), BorderLayout.CENTER);
		root.add(new LayerPanelView(), BorderLayout.WEST);

		frame.add(root);
		frame.setVisible(true);
	}

}
