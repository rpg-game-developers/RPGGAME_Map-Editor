package com.rpggame.mapeditor.controller.spritesheet;

import java.awt.image.BufferedImage;

import com.rpggame.mapeditor.view.tileselector.SelectionTile;

public class SpriteSheetBuilder {

	private BufferedImage spriteSheet;
	private int rows, cols;
	private int spriteWidth, spriteHeight;

	public SpriteSheetBuilder withSheet(BufferedImage img) {
		spriteSheet = img;
		return this;
	}

	public SpriteSheetBuilder withRows(int rows) {
		this.rows = rows;
		return this;
	}

	public SpriteSheetBuilder withColumns(int cols) {
		this.cols = cols;
		return this;
	}

	public SpriteSheetBuilder withSpriteSize(int width, int height) {
		this.spriteWidth = width;
		this.spriteHeight = height;
		return this;
	}

	protected int getCols() {
		return cols;
	}

	protected int getRows() {
		return rows;
	}

	protected int getSpriteHeight() {
		return spriteHeight;
	}

	protected BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	protected int getSpriteWidth() {
		return spriteWidth;
	}

	public SpriteSheet build() {

		int rows = getRows();
		int cols = getCols();

		BufferedImage sheet = getSpriteSheet();

		BufferedImage[][] sprites = new BufferedImage[cols][rows];
		for (int x = 0; x < cols; x++) {
			for (int y = 0; y < rows; y++) {
				sprites[x][y] = sheet.getSubimage(x * SelectionTile.SPRITE_SIZE, y * SelectionTile.SPRITE_SIZE,
						SelectionTile.SPRITE_SIZE, SelectionTile.SPRITE_SIZE);
			}
		}

		return new SpriteSheet(sprites);
	}
}
