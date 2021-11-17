package com.rpggame.mapeditor.view.filesystem;

import com.badlogic.gdx.Gdx;
import imgui.ImGui;

public class FileSystemView {
    public FileSystemView() {

    }

    public void imGui() {
        ImGui.begin("Files");

        DirectoryView directoryView = new DirectoryView(Gdx.files.local("../resources/"));
        directoryView.imGui();

        ImGui.end();
    }
}
