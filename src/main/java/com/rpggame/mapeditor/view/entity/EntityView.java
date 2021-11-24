package com.rpggame.mapeditor.view.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.view.ImGuiView;
import com.rpggame.rpggame.component.NameComp;
import com.rpggame.rpggame.entity.Entity;
import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;

import java.util.List;

public class EntityView implements ImGuiView {

    private final Entity root;
    private final Selector<Entity> entitySelector;

    public EntityView(Entity root, Selector<Entity> entitySelector) {
        this.root = root;
        this.entitySelector = entitySelector;
    }

    @Override
    public void imGui() {
        List<Entity> children = root.getChildren();

        int flags = ImGuiTreeNodeFlags.OpenOnArrow;

        if (children.isEmpty()) {
            flags |= ImGuiTreeNodeFlags.Leaf;
        }

        if (entitySelector.getSelected() == this.root) {
            flags |= ImGuiTreeNodeFlags.Selected;
            if (Gdx.input.isKeyJustPressed(Input.Keys.FORWARD_DEL)) {
                this.root.destroy();
                if (entitySelector.getSelected() == this.root) {
                    entitySelector.setSelected(null);
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                Entity clone = this.root.clone();
                this.root.getParent().addChild(clone);
            }
        }

        String name = "Entity";
        if (this.root.hasComponent(NameComp.class)) {
            name = this.root.getComponent(NameComp.class).getName();
        }

        boolean isOpen = ImGui.treeNodeEx(name, flags);
        if (ImGui.isItemClicked() && !ImGui.isItemToggledOpen()) {
            this.entitySelector.setSelected(this.root);
        }

        if (ImGui.beginDragDropSource()) {
            ImGui.setDragDropPayload(this.root);
            ImGui.text(name);
            ImGui.endDragDropSource();
        }

        if (ImGui.beginDragDropTarget()) {
            Entity entity = ImGui.acceptDragDropPayload(Entity.class);
            if (entity != null) {
                root.addChild(entity);
            }

            ImGui.endDragDropTarget();
        }

        if (ImGui.beginPopupContextItem()) {
            if (ImGui.menuItem("Add new entity")) {
                Entity newEntity = new Entity();
                newEntity.addComponent(new NameComp("Entity"));
                this.root.addChild(newEntity);
            }
            if (ImGui.menuItem("Clone", "Ctrl+D")) {
                Entity clone = this.root.clone();
                this.root.getParent().addChild(clone);
                ImGui.closeCurrentPopup();
            }
            if (ImGui.menuItem("Delete", "Del")) {
                this.root.destroy();
                ImGui.closeCurrentPopup();
                if (entitySelector.getSelected() == this.root) {
                    entitySelector.setSelected(null);
                }
            }
            ImGui.endPopup();
        }
        if (isOpen) {

            for (int i=0; i<children.size(); i++) {
                Entity entity = children.get(i);
                ImGui.pushID(i);

                ImGui.invisibleButton("Invisible Button", 400.0f, 5.0f);
                if (ImGui.beginDragDropTarget()) {
                    Entity payload = ImGui.acceptDragDropPayload(Entity.class);
                    if (payload != null) {
                        entity.addPrev(payload);
                    }

                    ImGui.endDragDropTarget();
                }

                EntityView entityView = new EntityView(entity, entitySelector);
                entityView.imGui();
                ImGui.popID();
            }

            ImGui.treePop();
        }
    }
}
