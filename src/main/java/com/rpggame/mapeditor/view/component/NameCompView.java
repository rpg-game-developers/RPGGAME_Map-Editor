package com.rpggame.mapeditor.view.component;

import com.rpggame.rpggame.component.NameComp;
import imgui.ImGui;
import imgui.type.ImString;

public class NameCompView extends ComponentView<NameComp> {
    private final ImString name;

    public NameCompView() {
        super(NameComp.class, NameComp::new, "Name");
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
