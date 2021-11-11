package com.rpggame.mapeditor.view;

import com.rpggame.mapeditor.controller.CameraController;
import com.rpggame.mapeditor.model.Camera;
import com.rpggame.mapeditor.model.tile.TileMap;
import com.rpggame.mapeditor.model.tile.TileSelector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

import static com.rpggame.mapeditor.constants.MapEditorConstants.TILE_SIZE;

public class MapEditingPanel extends JPanel {
	private List<TileMap> tileMaps;
	private TileSelector tileSelector;
	private CameraController cameraController;

	public MapEditingPanel(List<TileMap> tileMaps, TileSelector tileSelector) {
		this.tileMaps = tileMaps;
		this.tileSelector = tileSelector;
		this.cameraController = new CameraController();
		this.cameraController.connectComponent(this);

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
		Camera camera = cameraController.getCamera();
		int x = (int) camera.inverseX(e.getX()) / TILE_SIZE;
		int y = (int) camera.inverseY(e.getY()) / TILE_SIZE;
		if (SwingUtilities.isLeftMouseButton(e)) {
			tileMaps.get(0).setTile(x, y, tileSelector.getSelectedTile());
		}
		if (SwingUtilities.isRightMouseButton(e)) {
			tileMaps.get(0).setTile(x, y, null);
		}
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		Camera camera = cameraController.getCamera();
		camera.transformGraphics2D(g2);

		for (TileMap tileMap : tileMaps) {
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
