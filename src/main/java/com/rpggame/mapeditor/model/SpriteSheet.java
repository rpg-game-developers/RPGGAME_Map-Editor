package com.rpggame.mapeditor.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SpriteSheet {
    private String source;
    private Texture texture;
    private int rows;
    private int columns;
    private int tileSize;
    private int tileBorder;

    public SpriteSheet(String source, int rows, int columns, int tileSize, int tileBorder) {
        this.source = source;
        this.texture = new Texture(Gdx.files.internal(source));
        this.rows = rows;
        this.columns = columns;
        this.tileSize = tileSize;
        this.tileBorder = tileBorder;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getTextureID() {
        return texture.getTextureObjectHandle();
    }

    public Vector2[] getTileCoords(int row, int column) {
        float deltaX = 1.0f / (float) columns;
        float deltaY = 1.0f / (float) rows;
        float width = (float) (this.tileSize) / (float) texture.getWidth();
        float height = (float) (this.tileSize) / (float) texture.getHeight();
        Vector2[] result = new Vector2[2];
        result[0] = new Vector2();
        result[1] = new Vector2();
        result[0].x = deltaX * (float) column;
        result[0].y = deltaY * (float) row;
        result[1].x = result[0].x + width;
        result[1].y = result[0].y + height;
        return result;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.texture = new Texture(Gdx.files.internal(source));
        this.source = source;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getTileBorder() {
        return tileBorder;
    }

    public void setTileBorder(int tileBorder) {
        this.tileBorder = tileBorder;
    }
}
