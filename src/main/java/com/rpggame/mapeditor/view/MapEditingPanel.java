package com.rpggame.mapeditor.view;

import com.rpggame.mapeditor.controller.CameraController;
import com.rpggame.mapeditor.model.Camera;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.selector.SelectorListener;
import com.rpggame.mapeditor.model.tile.TileMap;
import com.rpggame.mapeditor.model.tile.TileSelector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static com.rpggame.mapeditor.constants.MapEditorConstants.TILE_SIZE;

public class MapEditingPanel extends JPanel {
	private Selector<TileMap> tileMapSelector;
	private TileSelector tileSelector;
	private CameraController cameraController;

	public MapEditingPanel(Selector<TileMap> tileMapSelector, TileSelector tileSelector) {
		this.tileMapSelector = tileMapSelector;
		this.tileSelector = tileSelector;
		this.cameraController = new CameraController();
		this.cameraController.connectComponent(this);

		tileMapSelector.subscribe(new SelectorListener<>() {
			@Override
			public void onListChange(List<TileMap> newList) {
				repaint();
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				handleMouseEvent(e);
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				handleMouseEvent(e);
			}
		});
	}

	private void handleMouseEvent(MouseEvent e) {
		if (tileMapSelector.getSelected() == null)
			return;

		Camera camera = cameraController.getCamera();
		int x = (int) camera.inverseX(e.getX()) / TILE_SIZE;
		int y = (int) camera.inverseY(e.getY()) / TILE_SIZE;
		if (SwingUtilities.isLeftMouseButton(e)) {
			tileMapSelector.getSelected().setTile(x, y, tileSelector.getSelected());
		}
		if (SwingUtilities.isRightMouseButton(e)) {
			tileMapSelector.getSelected().setTile(x, y, null);
		}
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		Camera camera = cameraController.getCamera();
		camera.transformGraphics2D(g2);

		for (TileMap tileMap : tileMapSelector.getList()) {
			// Don't render invisible layers.
			if (!tileMap.isVisible())
				continue;

			for (int i=0; i<tileMap.getWidth(); i++) {
				for (int j=0; j<tileMap.getHeight(); j++) {
					if (tileMap.getTile(i,j) != null) {
						g.drawImage(tileMap.getSpriteAt(i, j), i*TILE_SIZE, j*TILE_SIZE, null);
					}
				}
			}
		}
	}
}
