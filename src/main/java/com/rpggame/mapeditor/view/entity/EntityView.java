package com.rpggame.mapeditor.view.entity;

import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.view.entity.component.*;
import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.entity.Entity;
import imgui.ImGui;
import imgui.flag.ImGuiDataType;
import imgui.type.ImFloat;
import imgui.type.ImString;

import java.util.ArrayList;
import java.util.List;

public class EntityView {
    Selector<Entity> entitySelector;
    List<ComponentView<?>> components;

    public EntityView(Selector<Entity> entitySelector) {
        this.entitySelector = entitySelector;
        this.components = new ArrayList<>();
        this.components.add(new NameCompView());
        this.components.add(new TransformComponentView());
        this.components.add(new VelocityComponentView());
        this.components.add(new RectangleCollisionCompView());
        this.components.add(new SpriteCompView());
    }

    public void imGui() {
        ImGui.begin("Entity");

        Entity entity = entitySelector.getSelected();
        if (entity != null) {
            for (ComponentView<?> componentView : components) {
                componentView.imGui(entity);
            }
        }

        ImGui.end();
    }
}
