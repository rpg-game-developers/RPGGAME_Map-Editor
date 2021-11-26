package com.rpggame.mapeditor.view.component.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.rpggame.mapeditor.view.component.ComponentView;
import com.rpggame.rpggame.component.rendering.SpriteComp;
import imgui.ImGui;

public class SpriteCompView extends ComponentView<SpriteComp> {

    public SpriteCompView() {
        super(SpriteComp.class, SpriteComp::new, "Sprite");
    }

    @Override
    public void imGui() {
        if (comp.getSprite() != null) {
            String spritePath = comp.getSprite();
            Texture sprite = game.getSprites().getSprite(spritePath);
            int width = sprite.getWidth();
            int height = sprite.getHeight();
            ImGui.text("Image width: " + width);
            ImGui.text("Image height: " + height);
            ImGui.image(sprite.getTextureObjectHandle(), width, height, 0, 0, 1, 1);
            createDropTarget();
        }
        ImGui.button("Load sprite");
        createDropTarget();
    }

    private void createDropTarget() {
        if (ImGui.beginDragDropTarget()) {
            String fileHandle = ImGui.acceptDragDropPayload(String.class);
            if (fileHandle != null) {
                comp.setSprite(fileHandle);
            }

            ImGui.endDragDropTarget();
        }
    }
}
