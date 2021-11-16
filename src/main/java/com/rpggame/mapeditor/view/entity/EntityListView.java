package com.rpggame.mapeditor.view.entity;

import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.rpggame.component.NameComp;
import com.rpggame.rpggame.component.input.PlayerControllerComp;
import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.component.physics.VelocityComp;
import com.rpggame.rpggame.component.physics.collision.CollisionComp;
import com.rpggame.rpggame.component.rendering.SpriteComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityWorld;
import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;

public class EntityListView {
    EntityWorld world;
    Selector<Entity> entitySelector;

    public EntityListView(EntityWorld world, Selector<Entity> entitySelector) {
        this.world = world;
        this.entitySelector = entitySelector;
    }

    public void imGui() {
        ImGui.begin("Entities");

        if (ImGui.treeNodeEx("Entities")) {
            for (int i=0; i<world.getEntities().size(); i++) {
                Entity entity = world.getEntities().get(i);
                ImGui.pushID(i);

                int flags = ImGuiTreeNodeFlags.OpenOnArrow | ImGuiTreeNodeFlags.Leaf;

                if (entitySelector.getSelected() == entity) {
                    flags |= ImGuiTreeNodeFlags.Selected;
                }

                String name = "Entity";
                if (entity.hasComponent(NameComp.class)) {
                    name = entity.getComponent(NameComp.class).getName();
                }

                boolean isOpen = ImGui.treeNodeEx(name, flags);
                if (ImGui.isItemClicked() && !ImGui.isItemToggledOpen()) {
                    entitySelector.setSelected(entity);
                }
                if (ImGui.beginPopupContextItem()) {
                    if (ImGui.selectable("Clone")) {
                        Entity clone = entity.clone();
                        world.addEntity(clone);
                        ImGui.closeCurrentPopup();
                    }
                    if (ImGui.selectable("Delete")) {
                        world.removeEntity(entity);
                        ImGui.closeCurrentPopup();
                    }
                    ImGui.endPopup();
                }
                if (isOpen) {
                    ImGui.treePop();
                }
                ImGui.popID();
            }
            ImGui.treePop();
        }

        if (ImGui.button("Add entity")) {
            world.addEntity(new Entity());
        }

        ImGui.end();
    }
}
