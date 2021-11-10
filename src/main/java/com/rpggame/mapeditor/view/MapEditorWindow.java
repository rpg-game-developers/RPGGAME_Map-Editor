package com.rpggame.mapeditor.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rpggame.mapeditor.constants.FrameVariables;
import com.rpggame.mapeditor.controller.spritesheet.SpriteSheet;
import com.rpggame.mapeditor.controller.spritesheet.SpriteSheetBuilder;
import com.rpggame.mapeditor.model.MapTile;
import com.rpggame.mapeditor.view.history.HistoryView;
import com.rpggame.mapeditor.view.layerview.LayerPanelView;
import com.rpggame.mapeditor.view.tileselector.TileSelectorView;
import com.rpggame.mapeditor.view.topbar.TopBarView;

public class MapEditorWindow {

	private List<MapTile> tileList;
	private BufferedImage sheet;
	private SpriteSheet spriteSheet;

	public MapEditorWindow() {
		buildAndShowView();
	}

	public void buildAndShowView() {
		JFrame frame = new JFrame("Map Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration().getBounds();
		FrameVariables.FRAME_WIDTH = bounds.width;
		FrameVariables.FRAME_HEIGHT = bounds.height;
		frame.setSize(new Dimension(FrameVariables.FRAME_WIDTH, FrameVariables.FRAME_HEIGHT));

		// frame.setSize(new Dimension(1920, 1080));
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
		this.tileList.add(new MapTile("Background", "Water", 0, 2, 3));
		this.tileList.add(new MapTile("Forground", "Grass", 1, 0, 1));
		this.tileList.add(new MapTile("Forground", "Dirt", 1, 0, 2));
		this.tileList.add(new MapTile("Background", "Water", 0, 5, 3));
		this.tileList.add(new MapTile("Forground", "Grass", 1, 7, 1));
		this.tileList.add(new MapTile("Forground", "Dirt", 1, 10, 2));
		this.tileList.add(new MapTile("Background", "Water", 0, 1, 3));
		this.tileList.add(new MapTile("Forground", "Grass", 1, 21, 1));
		this.tileList.add(new MapTile("Forground", "Dirt", 1, 1, 2));
		this.tileList.add(new MapTile("Forground", "Grass", 1, 7, 1));
		this.tileList.add(new MapTile("Forground", "Dirt", 1, 10, 2));
		this.tileList.add(new MapTile("Background", "Water", 0, 1, 3));
		this.tileList.add(new MapTile("Forground", "Grass", 1, 21, 1));
		this.tileList.add(new MapTile("Forground", "Dirt", 1, 1, 2));
		this.tileList.add(new MapTile("Forground", "Dirt", 1, 10, 2));



		try {
			this.sheet = ImageIO.read(Objects
					.requireNonNull(MapEditorWindow.class.getResourceAsStream("/spriteAssets/testSpriteSheet.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.spriteSheet = new SpriteSheetBuilder().withSheet(this.sheet).withColumns(26).withRows(18).build();

		root.add(new TopBarView(), BorderLayout.NORTH);

		JPanel wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new BorderLayout());
		wrapperPanel.add(new HistoryView(), BorderLayout.NORTH);
		wrapperPanel.add(new TileSelectorView(this.tileList, this.spriteSheet), BorderLayout.CENTER);
		root.add(wrapperPanel, BorderLayout.EAST);
		root.add(new MapEditingPanel(), BorderLayout.CENTER);
		root.add(new LayerPanelView(tileList), BorderLayout.WEST);

		frame.add(root);
		frame.setVisible(true);
	}

}
