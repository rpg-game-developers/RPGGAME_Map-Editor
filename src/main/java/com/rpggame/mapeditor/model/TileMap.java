package com.rpggame.mapeditor.model;

import com.rpggame.mapeditor.controller.spritesheet.SpriteSheet;

import java.awt.image.BufferedImage;

public class TileMap {
    private final MapTile[][] tiles;
    private final int width;
    private final int height;
    private String type;
    private String name;
    private int level;
    private SpriteSheet spriteSheet;


    public TileMap(int width, int height) {
        this.tiles = new MapTile[width][height];
        this.width = width;
        this.height = height;
        this.level = 0;
        this.name = "";
        this.type = "";

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

    public void setTile(int column, int row, MapTile tile) {
        tiles[column][row] = tile;
    }

    public MapTile getTile(int column, int row) {
        return tiles[column][row];
    }

    public BufferedImage getSpriteAt(int column, int row) {
        MapTile tile = tiles[column][row];
        return spriteSheet.getSpriteAt(tile.getSheetRow(), tile.getSheetColumn());
    }
}
