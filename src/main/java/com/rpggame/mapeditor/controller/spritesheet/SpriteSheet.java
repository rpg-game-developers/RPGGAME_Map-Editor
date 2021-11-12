package com.rpggame.mapeditor.controller.spritesheet;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private final String source;
	private final BufferedImage[][] sprites;

	public SpriteSheet(String source, BufferedImage[][] sprites) {
		this.source = source;
		this.sprites = sprites;
	}

	public int getCount() {
		return sprites.length * sprites[0].length;
	}

	public String getSource() {
		return source;
	}

	public BufferedImage getSpriteAt(int row, int column) {
		if (row > sprites.length || column > sprites[row].length) {
			return sprites[0][0];
		}
		return sprites[row][column];
	}
}
