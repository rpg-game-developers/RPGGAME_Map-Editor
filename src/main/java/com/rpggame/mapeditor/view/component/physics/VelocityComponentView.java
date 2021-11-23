package com.rpggame.mapeditor.view.component.physics;

import com.rpggame.mapeditor.view.component.ComponentView;
import com.rpggame.rpggame.component.physics.VelocityComp;
import imgui.ImGui;
import imgui.flag.ImGuiDataType;
import imgui.type.ImFloat;

public class VelocityComponentView extends ComponentView<VelocityComp> {
    private ImFloat x;
    private ImFloat y;

    public VelocityComponentView() {
        super(VelocityComp.class, VelocityComp::new, "Velocity");
        this.x = new ImFloat();
        this.y = new ImFloat();
    }

    @Override
    public void imGui() {
        x.set(comp.getX());
        y.set(comp.getY());
        ImGui.inputScalar("X velocity", ImGuiDataType.Float, x);
        ImGui.inputScalar("Y velocity", ImGuiDataType.Float, y);
        comp.setX(x.get());
        comp.setY(y.get());
    }
}
