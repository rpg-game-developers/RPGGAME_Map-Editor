package com.rpggame.mapeditor.view.tileselector;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.rpggame.mapeditor.constants.MapEditorConstants;
import com.rpggame.mapeditor.controller.spritesheet.SpriteSheet;
import com.rpggame.mapeditor.model.Tile;
import com.rpggame.mapeditor.model.TileSelector;
import com.rpggame.mapeditor.utils.ImageHelper;

public class SelectionTile extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage spriteTile;

	public SelectionTile(boolean focus, int tileSize, Tile tile, SpriteSheet spriteSheet) {
		this.spriteTile = spriteSheet.getSpriteAt(tile.getSheetRow(), tile.getSheetColumn());

		this.spriteTile = ImageHelper.getImageWithMultipliedSize(spriteTile,
				(double) tileSize / (double) MapEditorConstants.TILE_SIZE);

		this.setPreferredSize(new Dimension(tileSize, tileSize));
		this.setMinimumSize(new Dimension(tileSize, tileSize));
		this.setMaximumSize(new Dimension(tileSize, tileSize));
		this.setBorder(BorderFactory.createLineBorder(focus ? Color.white : Color.black));

		this.revalidate();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.spriteTile != null) {
			g.drawImage(spriteTile, 0, 0, null);
		}
	}

}
