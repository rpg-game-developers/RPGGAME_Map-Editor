package com.rpggame.mapeditor.view.tileselector;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.rpggame.mapeditor.controller.spritesheet.SpriteSheet;
import com.rpggame.mapeditor.model.MapTile;

import static com.rpggame.mapeditor.constants.MapEditorConstants.TILE_SIZE;

public class SelectionTile extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final int SPRITE_SIZE = TILE_SIZE; // TODO extract to file for static variables

	private BufferedImage spriteTile;
	private int tileSize;

	public SelectionTile(int tileSize, MapTile tile, SpriteSheet spriteSheet) {
		this.tileSize = tileSize;

		this.spriteTile = spriteSheet.getSpriteAt(tile.getSheetColumn(), tile.getSheetRow());
		this.adjustTileImageSize();
		this.revalidate();

		this.setPreferredSize(new Dimension(tileSize, tileSize));
		this.setMinimumSize(new Dimension(tileSize, tileSize));
		this.setMaximumSize(new Dimension(tileSize, tileSize));
		this.setBackground(Color.red);
		this.setBorder(BorderFactory.createLineBorder(Color.black));

	}

	private void adjustTileImageSize() {
		if (tileSize != SPRITE_SIZE) {
			double sizeMultiplier = (double)tileSize / (double)SPRITE_SIZE;
			BufferedImage updatedTileImage = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_ARGB_PRE);
			AffineTransform at = new AffineTransform();
			at.scale(sizeMultiplier, sizeMultiplier);
			AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			updatedTileImage = scaleOp.filter(spriteTile, updatedTileImage);
			this.spriteTile = updatedTileImage;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.spriteTile != null) {
			g.drawImage(spriteTile, 0, 0, null);
		}
	}

}
