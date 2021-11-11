package com.rpggame.mapeditor.view;

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
	private Camera camera;
	private int lastMouseX;
	private int lastMouseY;

	public MapEditingPanel(List<TileMap> tileMaps, TileSelector tileSelector) {
		this.tileMaps = tileMaps;
		this.tileSelector = tileSelector;
		this.camera = new Camera();
		this.lastMouseX = 0;
		this.lastMouseY = 0;

		addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int notches = e.getWheelRotation();
				camera.zoom(Math.pow(0.9, (double)notches));
				repaint();
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lastMouseX = e.getX();
				lastMouseY = e.getY();
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
		int x = (int) camera.inverseX(e.getX()) / TILE_SIZE;
		int y = (int) camera.inverseY(e.getY()) / TILE_SIZE;
		if (SwingUtilities.isLeftMouseButton(e)) {
			tileMaps.get(0).setTile(x, y, tileSelector.getSelectedTile());
		}
		if (SwingUtilities.isRightMouseButton(e)) {
			tileMaps.get(0).setTile(x, y, null);
		}
		if (SwingUtilities.isMiddleMouseButton(e)) {
			int dx = e.getX() - lastMouseX;
			int dy = e.getY() - lastMouseY;
			camera.translate(dx/camera.getZoom(), dy/camera.getZoom());
		}
		lastMouseX = e.getX();
		lastMouseY = e.getY();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
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
