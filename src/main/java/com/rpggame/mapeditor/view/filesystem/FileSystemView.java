package com.rpggame.mapeditor.view.filesystem;

import com.badlogic.gdx.Gdx;
import com.rpggame.mapeditor.view.ImGuiView;
import imgui.ImGui;

public class FileSystemView implements ImGuiView {
    public FileSystemView() {

    }

    @Override
    public void imGui() {
        ImGui.begin("Files");

        ImGui.pushID(0);
        DirectoryView directoryView = new DirectoryView(Gdx.files.local("../resources/"));
        directoryView.imGui();
        ImGui.popID();

        ImGui.end();
    }
}
