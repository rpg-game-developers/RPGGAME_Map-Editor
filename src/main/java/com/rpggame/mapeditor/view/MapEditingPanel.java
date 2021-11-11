package com.rpggame.mapeditor.view;

import com.rpggame.mapeditor.model.Tile;
import com.rpggame.mapeditor.model.TileMap;
import com.rpggame.mapeditor.model.TileSelector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import static com.rpggame.mapeditor.constants.MapEditorConstants.TILE_SIZE;

public class MapEditingPanel extends JPanel {
	private List<TileMap> tileMaps;
	private TileSelector tileSelector;
	private float zoom;

	public MapEditingPanel(List<TileMap> tileMaps, TileSelector tileSelector) {
		this.tileMaps = tileMaps;
		this.tileSelector = tileSelector;
		this.zoom = 1.0f;
		addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int notches = e.getWheelRotation();
				System.out.println(notches);
				if (notches < 0) {
					zoom *= 1.1f;
				} else {
					zoom /= 1.1f;
				}
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
		int x = (int) (e.getX()/zoom) / TILE_SIZE;
		int y = (int) (e.getY()/zoom) / TILE_SIZE;
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

		g2.scale(zoom, zoom);
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
