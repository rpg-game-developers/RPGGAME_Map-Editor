package com.rpggame.mapeditor.model.selector;

import java.util.EventListener;

public interface SelectorListener<T> extends EventListener {
    void onSelectionChange(T newSelection);
}
