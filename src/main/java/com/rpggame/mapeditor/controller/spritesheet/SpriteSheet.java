package com.rpggame.mapeditor.controller.spritesheet;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private final BufferedImage[][] sprites;

	public SpriteSheet(BufferedImage[][] sprites) {
		this.sprites = sprites;
	}

	public int getCount() {
		return sprites.length * sprites[0].length;
	}

	public BufferedImage getSpriteAt(int row, int column) {
		if (row > sprites.length || column > sprites[row].length) {
			return sprites[0][0];
		}
		return sprites[row][column];
	}
}
