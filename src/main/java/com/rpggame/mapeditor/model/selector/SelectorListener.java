package com.rpggame.mapeditor.model.selector;

import java.util.EventListener;
import java.util.List;

public class SelectorListener<T> implements EventListener {

    public void onSelectionChange(T newSelection) {
    }

    public void onListChange(List<T> newList) {
    }

}
