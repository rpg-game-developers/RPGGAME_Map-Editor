package com.rpggame.mapeditor.view.filesystem;

import com.badlogic.gdx.files.FileHandle;
import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;

public class DirectoryView {
    FileHandle fileHandle;

    public DirectoryView(FileHandle fileHandle) {
        this.fileHandle = fileHandle;
    }

    public void imGui() {
        if (!fileHandle.exists())
            return;

        if(fileHandle.isDirectory()) {
            if (ImGui.treeNodeEx(fileHandle.name())) {
                FileHandle[] files = fileHandle.list();

                // display directories first
                for (FileHandle file : files) {
                    if (file.isDirectory()) {
                        DirectoryView directoryView = new DirectoryView(file);
                        directoryView.imGui();
                    }
                }

                // then display files
                for (FileHandle file : files) {
                    if (!file.isDirectory()) {
                        DirectoryView directoryView = new DirectoryView(file);
                        directoryView.imGui();
                    }
                }

                ImGui.treePop();
            }
        } else {
            if (ImGui.treeNodeEx(fileHandle.name(), ImGuiTreeNodeFlags.Leaf)) {
                if (ImGui.beginDragDropSource()) {
                    ImGui.setDragDropPayload(fileHandle.path());
                    ImGui.text(fileHandle.name());
                    ImGui.endDragDropSource();
                }
                ImGui.treePop();
            }
        }
    }
}
