package com.rpggame.mapeditor.model.tile;

import java.awt.image.BufferedImage;

import com.rpggame.mapeditor.constants.MapEditorConstants;
import com.rpggame.spritesheet.SpriteSheet;
import com.rpggame.spritesheet.SpriteSheetBuilder;

public class TileMap {
	private final Tile[][] tiles;
	private final int width;
	private final int height;
	private String type;
	private String name;
	private int level;
	private SpriteSheet spriteSheet;
	private boolean visible;

	public TileMap(TileMapJson tileMapJson) {
		this.spriteSheet = new SpriteSheetBuilder().withSheet(TileMap.class, tileMapJson.getSpriteSheet())
				.withColumns(26).withRows(18).build(MapEditorConstants.TILE_SIZE, MapEditorConstants.TILE_BORDER);
		this.width = tileMapJson.getWidth();
		this.height = tileMapJson.getHeight();
		this.tiles = new Tile[width][height];
		this.type = tileMapJson.getType();
		this.name = tileMapJson.getName();
		this.visible = true;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int tileX = tileMapJson.getTileX()[i][j];
				int tileY = tileMapJson.getTileY()[i][j];
				if (tileX == -1 || tileY == -1) {
					this.tiles[i][j] = null;
				} else {
					this.tiles[i][j] = new Tile(tileY, tileX);
				}
			}
		}
	}

	public TileMap(String name, SpriteSheet spriteSheet, int width, int height) {
		this.spriteSheet = spriteSheet;
		this.width = width;
		this.height = height;
		this.level = 0;
		this.name = name;
		this.type = "";
		this.tiles = new Tile[width][height];
		this.visible = true;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				this.tiles[i][j] = null;
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setTile(int column, int row, Tile tile) {
		if (column < 0 || column >= width || row < 0 || row >= height)
			return;

		tiles[column][row] = tile;
	}

	public Tile getTile(int column, int row) {
		if (column < 0 || column >= width || row < 0 || row >= height)
			return null;

		return tiles[column][row];
	}

	public BufferedImage getSpriteAt(int column, int row) {
		Tile tile = tiles[column][row];
		return spriteSheet.getSpriteAt(tile.getSheetRow(), tile.getSheetColumn());
	}
}
