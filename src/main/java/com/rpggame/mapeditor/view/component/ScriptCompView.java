package com.rpggame.mapeditor.view.component;

import com.rpggame.rpggame.component.ScriptComp;
import imgui.ImGui;
import imgui.type.ImString;

public class ScriptCompView extends ComponentView<ScriptComp> {
    ImString filePath;

    public ScriptCompView() {
        super(ScriptComp.class, ScriptComp::new, "Script");
        this.filePath = new ImString();
    }

    @Override
    public void imGui() {
        filePath.set(comp.getFilePath());
        ImGui.pushID(0);
        ImGui.inputText("Script file", filePath);
        ImGui.popID();
        comp.setFilePath(filePath.get());
        
        createDropTarget();
    }


    private void createDropTarget() {
        if (ImGui.beginDragDropTarget()) {
            String fileHandle = ImGui.acceptDragDropPayload(String.class);
            if (fileHandle != null) {
                comp.setFilePath(fileHandle);
            }
            ImGui.endDragDropTarget();
        }
    }
}
