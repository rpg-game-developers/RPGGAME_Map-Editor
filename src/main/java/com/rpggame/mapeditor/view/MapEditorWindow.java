package com.rpggame.mapeditor.view;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rpggame.mapeditor.constants.FrameVariables;
import com.rpggame.mapeditor.controller.spritesheet.SpriteSheet;
import com.rpggame.mapeditor.controller.spritesheet.SpriteSheetBuilder;
import com.rpggame.mapeditor.model.MapTile;
import com.rpggame.mapeditor.view.layerview.LayerPanelView;
import com.rpggame.mapeditor.view.tileselector.TileSelectorView;

public class MapEditorWindow {

	private List<MapTile> tileList;
	private BufferedImage sheet;
	private SpriteSheet spriteSheet;

	public MapEditorWindow() {
		buildAndShowView();
	}

	public void buildAndShowView() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		System.out.println(frame.getWidth());
		FrameVariables.FRAME_WIDTH = frame.getWidth();
		FrameVariables.FRAME_HEIGHT = frame.getHeight();
		//frame.setSize(new Dimension(1920, 1080));
		frame.setLocationRelativeTo(null);

		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());

		// TODO outsource tileList creation
		this.tileList = new ArrayList<>();

		this.tileList.add(new MapTile("Background", "Water", 0, 2, 3));
		this.tileList.add(new MapTile("Forground", "Grass", 1, 0, 1));
		this.tileList.add(new MapTile("Forground", "Dirt", 1, 0, 2));
		this.tileList.add(new MapTile("Background", "Water", 0, 5, 3));
		this.tileList.add(new MapTile("Forground", "Grass", 1, 7, 1));
		this.tileList.add(new MapTile("Forground", "Dirt", 1, 10, 2));
		this.tileList.add(new MapTile("Background", "Water", 0, 1, 3));
		this.tileList.add(new MapTile("Forground", "Grass", 1, 21, 1));
		this.tileList.add(new MapTile("Forground", "Dirt", 1, 1, 2));

		try {
			this.sheet = ImageIO.read(MapEditorWindow.class.getResourceAsStream("/spriteAssets/testSpriteSheet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.spriteSheet = new SpriteSheetBuilder().withSheet(this.sheet).withColumns(26).withRows(18).build();

		root.add(new TileSelectorView(this.tileList, this.spriteSheet), BorderLayout.EAST);
		root.add(new MapEditingPanel(), BorderLayout.CENTER);
		root.add(new LayerPanelView(tileList), BorderLayout.WEST);

		frame.add(root);
		frame.setVisible(true);
	}

}
