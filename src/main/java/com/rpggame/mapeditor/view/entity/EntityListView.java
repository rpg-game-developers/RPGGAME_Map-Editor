package com.rpggame.mapeditor.view.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.rpggame.component.NameComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityWorld;
import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;

public class EntityListView {
    private EntityWorld world;
    private Selector<Entity> entitySelector;

    public EntityListView(EntityWorld world, Selector<Entity> entitySelector) {
        this.world = world;
        this.entitySelector = entitySelector;
    }

    public void imGui() {
        ImGui.begin("Entities");

        ImGui.pushID(0);
        EntityView entityView = new EntityView(world.getRoot(), entitySelector);
        entityView.imGui();
        ImGui.popID();

        ImGui.end();
    }
}
