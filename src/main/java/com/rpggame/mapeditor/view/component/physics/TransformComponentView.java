package com.rpggame.mapeditor.view.component.physics;

import com.badlogic.gdx.math.Vector2;
import com.rpggame.mapeditor.view.component.ComponentView;
import com.rpggame.rpggame.component.physics.TransformComp;
import imgui.ImGui;
import imgui.flag.ImGuiDataType;
import imgui.type.ImFloat;

public class TransformComponentView extends ComponentView<TransformComp> {
    private final ImFloat x;
    private final ImFloat y;
    private final ImFloat xScale;
    private final ImFloat yScale;
    private final ImFloat rotation;

    public TransformComponentView() {
        super(TransformComp.class, TransformComp::new, "Transform");
        this.x = new ImFloat();
        this.y = new ImFloat();
        this.xScale = new ImFloat();
        this.yScale = new ImFloat();
        this.rotation = new ImFloat();
    }

    @Override
    public void imGui() {
        Vector2 position = comp.getPosition();
        Vector2 scale = comp.getScale();
        float rotationRad = comp.getRotation();

        x.set(position.x);
        y.set(position.y);
        ImGui.pushID(0);
        ImGui.text("Position");
        ImGui.inputScalar("X", ImGuiDataType.Float, x);
        ImGui.inputScalar("Y", ImGuiDataType.Float, y);
        ImGui.popID();

        xScale.set(scale.x);
        yScale.set(scale.y);
        ImGui.pushID(1);
        ImGui.text("Scale");
        ImGui.inputScalar("X", ImGuiDataType.Float, xScale);
        ImGui.inputScalar("Y", ImGuiDataType.Float, yScale);
        ImGui.popID();

        rotation.set(rotationRad);
        ImGui.pushID(2);
        ImGui.text("Rotation");
        ImGui.inputScalar("Radians", ImGuiDataType.Float, rotation);
        ImGui.popID();

        if (position.x != x.get() || position.y != y.get()) {
            comp.setPosition(new Vector2(x.get(), y.get()));
        }
        if (scale.x != xScale.get() || scale.y != yScale.get()) {
            comp.setScale(new Vector2(xScale.get(), yScale.get()));
        }
        if (rotationRad != rotation.get()) {
            comp.setRotation(rotation.get());
        }
    }
}
