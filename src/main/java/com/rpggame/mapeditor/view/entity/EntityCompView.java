package com.rpggame.mapeditor.view.entity;

import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.view.ImGuiView;
import com.rpggame.mapeditor.view.component.*;
import com.rpggame.mapeditor.view.component.input.PlayerControllerCompView;
import com.rpggame.mapeditor.view.component.loot.SingleItemLootTableCompView;
import com.rpggame.mapeditor.view.component.physics.TransformComponentView;
import com.rpggame.mapeditor.view.component.physics.VelocityComponentView;
import com.rpggame.mapeditor.view.component.physics.collision.RectangleCollisionCompView;
import com.rpggame.mapeditor.view.component.rendering.SpriteCompView;
import com.rpggame.mapeditor.view.component.rendering.TileMapCompView;
import com.rpggame.rpggame.EntityApplicationAdapter;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.entity.Entity;
import imgui.ImGui;

import java.util.ArrayList;
import java.util.List;

public class EntityCompView implements ImGuiView {
    private final Selector<Entity> entitySelector;
    private final List<ComponentView<?>> components;
    private final Selector<ComponentView<?>> componentSelector;

    public EntityCompView(EntityApplicationAdapter game, Selector<Entity> entitySelector) {
        this.entitySelector = entitySelector;
        this.componentSelector = new Selector<>();
        this.components = new ArrayList<>();
        this.components.add(new NameCompView());
        this.components.add(new TransformComponentView());
        this.components.add(new VelocityComponentView());
        this.components.add(new RectangleCollisionCompView());
        this.components.add(new HealthCompView());
        this.components.add(new PlayerControllerCompView());
        this.components.add(new SpriteCompView());
        this.components.add(new TileMapCompView());
        this.components.add(new ScriptCompView());
        this.components.add(new SingleItemLootTableCompView());


        for (ComponentView<?> componentView : components) {
            componentView.setGame(game);
        }
    }

    @Override
    public void imGui() {
        ImGui.begin("Entity");

        Entity entity = entitySelector.getSelected();
        if (entity != null) {
            if (ImGui.button("Add component")) {
                ImGui.openPopup("Add component");
            }

            for (ComponentView<?> componentView : components) {
                componentView.imGui(entity);
            }
        }

        if (ImGui.beginPopupModal("Add component")) {

            if (ImGui.isWindowAppearing()) {
                ImGui.setWindowSize(400, 200);
            }

            if (ImGui.beginListBox("Component")) {
                for (ComponentView<?> componentView : components) {
                    boolean selected = componentSelector.getSelected() == componentView;
                    if (ImGui.selectable(componentView.getName(), selected)) {
                        componentSelector.setSelected(componentView);
                    }
                }
                ImGui.endListBox();
            }
            if (ImGui.button("Add")) {
                ComponentView<?> selected = componentSelector.getSelected();
                if (selected != null) {
                    Component comp = componentSelector.getSelected().createComponent();
                    entitySelector.getSelected().addComponent(comp);
                    ImGui.closeCurrentPopup();
                }
            }
            ImGui.sameLine();
            if (ImGui.button("Cancel")) {
                ImGui.closeCurrentPopup();
            }
            ImGui.endPopup();
        }

        ImGui.end();
    }
}
