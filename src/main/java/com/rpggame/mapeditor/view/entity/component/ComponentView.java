package com.rpggame.mapeditor.view.entity.component;

import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.entity.Entity;
import imgui.ImGui;

import java.util.function.Supplier;

public abstract class ComponentView<T extends Component> {
    private Class<T> componentType;
    private Supplier<T> supplier;
    private String name;
    protected T comp;

    public ComponentView(Class<T> componentType, Supplier<T> supplier, String name) {
        this.supplier = supplier;
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

    public String getName() {
        return name;
    }

    public T createComponent() {
        return supplier.get();
    }

}
