package com.rpggame.mapeditor.view.component;

import com.badlogic.gdx.Game;
import com.rpggame.mapeditor.view.ImGuiView;
import com.rpggame.rpggame.EntityApplicationAdapter;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.entity.Entity;
import imgui.ImGui;
import imgui.type.ImBoolean;

import java.util.function.Supplier;

public abstract class ComponentView<T extends Component> implements ImGuiView {
    private Class<T> componentType;
    private Supplier<T> supplier;
    private String name;
    protected T comp;
    protected EntityApplicationAdapter game;

    public ComponentView(Class<T> componentType, Supplier<T> supplier, String name) {
        this.supplier = supplier;
        this.componentType = componentType;
        this.name = name;
        this.game = null;
    }

    public void setGame(EntityApplicationAdapter game) {
        this.game = game;
    }

    @Override
    public abstract void imGui();

    public void imGui(Entity entity) {
        if (entity.hasComponent(componentType)) {
            ImBoolean isOpen = new ImBoolean(true);
            if (ImGui.collapsingHeader(name, isOpen)) {
                this.comp = entity.getComponent(componentType);
                imGui();
            }
            if (!isOpen.get()) {
                entity.removeComponent(componentType);
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
