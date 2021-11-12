package com.rpggame.mapeditor.model.selector;

import java.util.*;

public class Selector<T>  {
    private List<T> list;
    private T selected;
    private Collection<SelectorListener<T>> listeners;

    public Selector() {
        this.list = new ArrayList<>();
        this.selected = null;
        this.listeners = new HashSet<>();
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> newList) {
        this.list = newList;
        for(SelectorListener<T> listener : listeners){
            listener.onListChange(newList);
        }
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
