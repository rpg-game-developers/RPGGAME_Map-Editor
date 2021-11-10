package com.rpggame.mapeditor.utils;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ImageHelper {

	public static BufferedImage getImageWithMultipliedSize(BufferedImage imageToDraw, double multiplier) {
		BufferedImage updatedTileImage = new BufferedImage(imageToDraw.getWidth() * (int) multiplier,
				imageToDraw.getHeight() * (int) multiplier, BufferedImage.TYPE_INT_ARGB_PRE);
		AffineTransform at = new AffineTransform();
		at.scale(multiplier, multiplier);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		updatedTileImage = scaleOp.filter(imageToDraw, updatedTileImage);
		return updatedTileImage;

	}

}
