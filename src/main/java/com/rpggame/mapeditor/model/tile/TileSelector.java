package com.rpggame.mapeditor.model.tile;

import com.rpggame.mapeditor.model.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileSelector {
    private List<Tile> tileList;
    private Tile selectedTile;

    public TileSelector(int rows, int collumns) {
        selectedTile = null;
        this.tileList = new ArrayList<>();
        for (int i=0; i<rows; i++) {
            for (int j=0; j<collumns; j++) {
                this.tileList.add(new Tile(i, j));
            }
        }
    }

    public List<Tile> getTiles() {
        return tileList;
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedTile(Tile selectedTile) {
        this.selectedTile = selectedTile;
    }
}
