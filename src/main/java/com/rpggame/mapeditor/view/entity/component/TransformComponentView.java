package com.rpggame.mapeditor.view.entity.component;

import com.rpggame.rpggame.component.physics.TransformComp;
import imgui.ImGui;
import imgui.flag.ImGuiDataType;
import imgui.type.ImFloat;
import org.lwjgl.system.CallbackI;

public class TransformComponentView extends ComponentView<TransformComp> {
    private ImFloat x;
    private ImFloat y;

    public TransformComponentView() {
        super(TransformComp.class, TransformComp::new, "Transform");
        this.x = new ImFloat();
        this.y = new ImFloat();
    }

    @Override
    public void imGui() {
        x.set(comp.getX());
        y.set(comp.getY());
        ImGui.inputScalar("X", ImGuiDataType.Float, x);
        ImGui.inputScalar("Y", ImGuiDataType.Float, y);
        comp.setX(x.get());
        comp.setY(y.get());
    }
}
