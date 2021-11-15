package com.rpggame.mapeditor.view.entity.component;

import com.rpggame.rpggame.component.rendering.SpriteComp;
import imgui.ImGui;

public class SpriteCompView extends ComponentView<SpriteComp> {

    public SpriteCompView() {
        super(SpriteComp.class, "Sprite");
    }

    @Override
    public void imGui() {
        ImGui.text(comp.getSprite().getTextureData().getFormat().name());
        ImGui.text("Width: " + comp.getSprite().getTextureData().getWidth());
        ImGui.text("Height: " + comp.getSprite().getTextureData().getHeight());
    }
}
