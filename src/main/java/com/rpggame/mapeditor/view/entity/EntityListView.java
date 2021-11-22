package com.rpggame.mapeditor.view.entity;

import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.view.ImGuiView;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityWorld;
import imgui.ImGui;
import imgui.flag.ImGuiStyleVar;

public class EntityListView implements ImGuiView {
    private final EntityWorld world;
    private final Selector<Entity> entitySelector;

    public EntityListView(EntityWorld world, Selector<Entity> entitySelector) {
        this.world = world;
        this.entitySelector = entitySelector;
    }

    @Override
    public void imGui() {
        ImGui.begin("Entities");

        ImGui.pushID(0);
        ImGui.pushStyleVar(ImGuiStyleVar.ItemSpacing, 0.0f, 0.0f);
        EntityView entityView = new EntityView(world.getRoot(), entitySelector);
        entityView.imGui();
        ImGui.popStyleVar();
        ImGui.popID();

        ImGui.end();
    }
}
