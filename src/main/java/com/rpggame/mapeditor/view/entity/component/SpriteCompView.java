package com.rpggame.mapeditor.view.entity.component;

import com.badlogic.gdx.graphics.Texture;
import com.rpggame.rpggame.component.rendering.SpriteComp;
import imgui.ImGui;

public class SpriteCompView extends ComponentView<SpriteComp> {

    public SpriteCompView() {
        super(SpriteComp.class, SpriteComp::new, "Sprite");
    }

    @Override
    public void imGui() {
        if (comp.getSprite() != null) {
            int width = comp.getSprite().getTextureData().getWidth();
            int height = comp.getSprite().getTextureData().getHeight();
            ImGui.text("Image width: " + width);
            ImGui.text("Image height: " + height);
            ImGui.image(comp.getSprite().getTextureObjectHandle(), width, height, 0, 0, 1, 1);
            createDropTarget();
        }
        ImGui.button("Load sprite");
        createDropTarget();
    }

    private void createDropTarget() {
        if (ImGui.beginDragDropTarget()) {
            String fileHandle = ImGui.acceptDragDropPayload(String.class);
            if (fileHandle != null) {
                comp.setSprite(new Texture(fileHandle));
            }

            ImGui.endDragDropTarget();
        }
    }
}
