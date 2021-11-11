package com.rpggame.mapeditor.model;

import com.rpggame.mapeditor.model.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Selector<T> {
    private List<T> list;
    private T selected;

    public Selector() {
        list = new ArrayList<>();
        selected = null;
    }

    public List<T> getList() {
        return list;
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T newSelected) {
        selected = newSelected;
    }

    public void resetSelected() {
        selected = null;
    }
}
