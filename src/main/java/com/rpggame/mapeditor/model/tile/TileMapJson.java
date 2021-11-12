package com.rpggame.mapeditor.model.tile;


import lombok.Data;

@Data
public class TileMapJson {
    private String name;
    private String type;
    private String spriteSheet;
    private int width;
    private int height;
    private int[][] tileX;
    private int[][] tileY;

    public TileMapJson(TileMap tileMap) {
        this.name = tileMap.getName();
        this.type = tileMap.getType();
        this.spriteSheet = tileMap.getSpriteSheet().getSource();
        this.width = tileMap.getWidth();
        this.height = tileMap.getHeight();
        this.tileX = new int[this.width][this.height];
        this.tileY = new int[this.width][this.height];

        for (int i=0; i<this.width; i++) {
            for (int j=0; j<this.height; j++) {
                Tile tile = tileMap.getTile(i,j);
                if (tile == null) {
                    this.tileX[i][j] = -1;
                    this.tileY[i][j] = -1;
                } else {
                    this.tileX[i][j] = tile.getSheetColumn();
                    this.tileY[i][j] = tile.getSheetRow();
                }
            }
        }
    }
}
