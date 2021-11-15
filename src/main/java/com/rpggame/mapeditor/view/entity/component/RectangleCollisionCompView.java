package com.rpggame.mapeditor.view.entity.component;

import com.badlogic.gdx.math.Vector2;
import com.rpggame.rpggame.component.physics.collision.RectangleCollisionComp;
import imgui.ImGui;
import imgui.flag.ImGuiDataType;
import imgui.type.ImFloat;

public class RectangleCollisionCompView extends ComponentView<RectangleCollisionComp> {
    private ImFloat x;
    private ImFloat y;

    public RectangleCollisionCompView() {
        super(RectangleCollisionComp.class, "Rectangle collision");
        this.x = new ImFloat();
        this.y = new ImFloat();
    }

    @Override
    public void imGui() {
        x.set(comp.getSize().x);
        y.set(comp.getSize().y);
        ImGui.inputScalar("Width", ImGuiDataType.Float, x);
        ImGui.inputScalar("Height", ImGuiDataType.Float, y);
        comp.setSize(new Vector2(x.get(), y.get()));
    }
}
