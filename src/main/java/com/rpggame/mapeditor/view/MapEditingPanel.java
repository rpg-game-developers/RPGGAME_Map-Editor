package com.rpggame.mapeditor.view;

import com.rpggame.mapeditor.model.Tile;
import com.rpggame.mapeditor.model.TileMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static com.rpggame.mapeditor.constants.MapEditorConstants.TILE_SIZE;

public class MapEditingPanel extends JPanel {
	private List<TileMap> tileMaps;

	public MapEditingPanel(List<TileMap> tileMaps) {
		this.tileMaps = tileMaps;
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getX() / TILE_SIZE;
				int y = e.getY() / TILE_SIZE;
				tileMaps.get(0).setTile(x, y, new Tile(0, 0));
				repaint();
			}
		});
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
