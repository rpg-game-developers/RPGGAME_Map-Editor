package com.rpggame.mapeditor.view.entity.component;

import com.rpggame.rpggame.component.NameComp;
import imgui.ImGui;
import imgui.type.ImString;

public class NameCompView extends ComponentView<NameComp> {
    ImString name;

    public NameCompView() {
        super(NameComp.class, "Name");
        this.name = new ImString();
    }

    @Override
    public void imGui() {
        name.set(comp.getName());
        ImGui.pushID(0);
        ImGui.inputText("Name", name);
        ImGui.popID();
        comp.setName(name.get());
    }
}
