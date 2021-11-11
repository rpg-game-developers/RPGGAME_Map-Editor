package com.rpggame.mapeditor.view;

import com.rpggame.mapeditor.model.Tile;
import com.rpggame.mapeditor.model.TileMap;
import com.rpggame.mapeditor.model.TileSelector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static com.rpggame.mapeditor.constants.MapEditorConstants.TILE_SIZE;

public class MapEditingPanel extends JPanel {
	private List<TileMap> tileMaps;
	private TileSelector tileSelector;

	public MapEditingPanel(List<TileMap> tileMaps, TileSelector tileSelector) {
		this.tileMaps = tileMaps;
		this.tileSelector = tileSelector;
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
		int x = e.getX() / TILE_SIZE;
		int y = e.getY() / TILE_SIZE;
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
