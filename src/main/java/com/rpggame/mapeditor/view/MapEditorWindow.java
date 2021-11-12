package com.rpggame.mapeditor.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.tile.TileMap;
import com.rpggame.mapeditor.model.tile.TileSelector;
import com.rpggame.mapeditor.view.history.HistoryView;
import com.rpggame.mapeditor.view.layerview.LayerPanelView;
import com.rpggame.mapeditor.view.tileselector.TileSelectorView;
import com.rpggame.mapeditor.view.topbar.TopBarView;

public class MapEditorWindow {

	private TileSelector tileSelector;
	private Selector<TileMap> tileMapSelector;
	private BufferedImage sheet1;
	private SpriteSheet spriteSheet1;
	private BufferedImage sheet2;
	private SpriteSheet spriteSheet2;
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

		this.spriteSheet1 = new SpriteSheetBuilder().withSheet("/spriteAssets/rogueLikeSheet_transparent.png").withColumns(26).withRows(18).build();
		this.spriteSheet2 = new SpriteSheetBuilder().withSheet("/spriteAssets/testSpriteSheet.png").withColumns(26).withRows(18).build();

		this.tileMapSelector = new Selector<>();
		this.tileMapSelector.getList().add(new TileMap("ground", this.spriteSheet1, 100, 100));
		this.tileMapSelector.getList().add(new TileMap("furniture", this.spriteSheet2, 100, 100));

		// tile map
		TileSelectorView tileSelectorView = new TileSelectorView(this.tileSelector, this.tileMapSelector);
		HistoryView historyView = new HistoryView();

		MapEditingPanel mapEditingPanel = new MapEditingPanel(this.tileMapSelector, this.tileSelector);

		root.add(new TopBarView(new TopBarController(this::updateEditorWindow, tileSelectorView, historyView, tileMapSelector)), BorderLayout.NORTH);

		JPanel wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new BorderLayout());
		wrapperPanel.add(historyView, BorderLayout.NORTH);
		wrapperPanel.add(tileSelectorView, BorderLayout.CENTER);
		root.add(wrapperPanel, BorderLayout.EAST);
		root.add(mapEditingPanel, BorderLayout.CENTER);
		root.add(new LayerPanelView(tileMapSelector), BorderLayout.WEST);

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
