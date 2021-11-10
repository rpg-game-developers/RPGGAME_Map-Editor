package com.rpggame.mapeditor.view;

import com.rpggame.mapeditor.controller.spritesheet.SpriteSheet;
import com.rpggame.mapeditor.model.MapTile;
import com.rpggame.mapeditor.model.TileMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static com.rpggame.mapeditor.constants.MapEditorConstants.TILE_SIZE;

public class MapEditingPanel extends JPanel {
	private List<TileMap> tileMaps;

	public MapEditingPanel() {
		this.tileMaps = new ArrayList<TileMap>();
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getX() / TILE_SIZE;
				int y = e.getY() / TILE_SIZE;
				tileMaps.get(0).setTile(x, y, new MapTile("Background", "Water", 0, 0, 0));
				repaint();
			}
		});
	}

	public void addTileMap(TileMap tileMap) {
		tileMaps.add(tileMap);
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
