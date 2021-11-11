package com.rpggame.mapeditor.model.tile;

import com.rpggame.mapeditor.controller.spritesheet.SpriteSheet;
import com.rpggame.mapeditor.model.tile.Tile;

import java.awt.image.BufferedImage;

public class TileMap {
    private final Tile[][] tiles;
    private final int width;
    private final int height;
    private String type;
    private String name;
    private int level;
    private SpriteSheet spriteSheet;


    public TileMap(String name, SpriteSheet spriteSheet, int width, int height) {
        this.spriteSheet = spriteSheet;
        this.width = width;
        this.height = height;
        this.level = 0;
        this.name = name;
        this.type = "";
        this.tiles = new Tile[width][height];
        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
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
