package com.rpggame.mapeditor.model.tile;

import com.rpggame.mapeditor.model.selector.Selector;

public class TileSelector extends Selector<Tile> {
    public TileSelector(int rows, int columns) {
        super();
        for (int j=0; j<columns; j++) {
            for (int i=0; i<rows; i++) {
                this.getList().add(new Tile(i, j));
            }
        }
    }
}
