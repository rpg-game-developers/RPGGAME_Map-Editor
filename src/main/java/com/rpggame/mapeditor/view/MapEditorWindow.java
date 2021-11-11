package com.rpggame.mapeditor.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.rpggame.mapeditor.constants.FrameVariables;
import com.rpggame.mapeditor.controller.TopBarController;
import com.rpggame.mapeditor.controller.spritesheet.SpriteSheet;
import com.rpggame.mapeditor.controller.spritesheet.SpriteSheetBuilder;
import com.rpggame.mapeditor.model.tile.TileMap;
import com.rpggame.mapeditor.model.tile.TileSelector;
import com.rpggame.mapeditor.view.history.HistoryView;
import com.rpggame.mapeditor.view.layerview.LayerPanelView;
import com.rpggame.mapeditor.view.tileselector.TileSelectorView;
import com.rpggame.mapeditor.view.topbar.TopBarView;

public class MapEditorWindow {

	private TileSelector tileSelector;
	private List<TileMap> tileMapList;
	private BufferedImage sheet;
	private SpriteSheet spriteSheet;
	private JFrame frame;

	public MapEditorWindow() {
		buildAndShowView();
	}

	public void buildAndShowView() {
		this.frame = new JFrame("Map Editor");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);

		Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration().getBounds();
		FrameVariables.FRAME_WIDTH = bounds.width;
		FrameVariables.FRAME_HEIGHT = bounds.height;
		this.frame.setExtendedState(this.frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.frame.setSize(new Dimension(FrameVariables.FRAME_WIDTH, FrameVariables.FRAME_HEIGHT));

		// this.frame.setSize(new Dimension(1920, 1080));
		this.frame.setLocationRelativeTo(null);

		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());

		this.tileSelector = new TileSelector(26, 18);

		try {
			this.sheet = ImageIO.read(Objects
					.requireNonNull(MapEditorWindow.class.getResourceAsStream("/spriteAssets/testSpriteSheet.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.spriteSheet = new SpriteSheetBuilder().withSheet(this.sheet).withColumns(26).withRows(18).build();

		this.tileMapList = new ArrayList<TileMap>();
		this.tileMapList.add(new TileMap("water", this.spriteSheet, 100, 100));

		// tile map
		MapEditingPanel mapEditingPanel = new MapEditingPanel(this.tileMapList, this.tileSelector);

		root.add(new TopBarView(new TopBarController(this::updateEditorWindow)), BorderLayout.NORTH);

		JPanel wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new BorderLayout());
		wrapperPanel.add(new HistoryView(), BorderLayout.NORTH);
		wrapperPanel.add(new TileSelectorView(this.tileSelector, this.spriteSheet), BorderLayout.CENTER);
		root.add(wrapperPanel, BorderLayout.EAST);
		root.add(mapEditingPanel, BorderLayout.CENTER);
		root.add(new LayerPanelView(tileMapList), BorderLayout.WEST);

		this.frame.add(root);
		this.frame.setVisible(true);
	}

	public void updateEditorWindow(String newSize) {
		List<Integer> splittedSize = Arrays.stream(newSize.split("x")).map(Integer::parseInt)
				.collect(Collectors.toList());
		FrameVariables.FRAME_WIDTH = splittedSize.get(0);
		FrameVariables.FRAME_HEIGHT = splittedSize.get(1);
		this.frame.setSize(new Dimension(FrameVariables.FRAME_WIDTH, FrameVariables.FRAME_HEIGHT));
		SwingUtilities.updateComponentTreeUI(this.frame);
		this.frame.invalidate();
		this.frame.validate();
		this.frame.repaint();
		this.frame.setVisible(false);
		this.frame.setVisible(true);
	}

	
	
}
