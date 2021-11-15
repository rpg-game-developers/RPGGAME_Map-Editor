package com.rpggame.mapeditor.view.entity.component;

import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.entity.Entity;
import imgui.ImGui;

public abstract class ComponentView<T> {
    private Class<T> componentType;
    private String name;
    protected T comp;

    public ComponentView(Class<T> componentType, String name) {
        this.componentType = componentType;
        this.name = name;
    }

    public abstract void imGui();

    public void imGui(Entity entity) {
        if (entity.hasComponent(componentType)) {
            if (ImGui.collapsingHeader(name)) {
                this.comp = entity.getComponent(componentType);
                imGui();
            }
        }
    }
}
