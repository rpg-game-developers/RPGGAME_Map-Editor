package com.rpggame.mapeditor.model.selector;

import java.util.*;

public class Selector<T>  {
    private T selected;
    private Collection<SelectorListener<T>> listeners;

    public Selector() {
        this.selected = null;
        this.listeners = new HashSet<>();
    }

    public void subscribe(SelectorListener<T> listener) {
        listeners.add(listener);
    }

    public void unsubscribe(SelectorListener<T> listener) {
        listeners.remove(listener);
    }

    public T getSelected() {
        return this.selected;
    }

    public void setSelected(T newSelected) {
        this.selected = newSelected;
        for(SelectorListener<T> listener : listeners){
            listener.onSelectionChange(newSelected);
        }
    }

    public void resetSelected() {
        this.selected = null;
    }
}
