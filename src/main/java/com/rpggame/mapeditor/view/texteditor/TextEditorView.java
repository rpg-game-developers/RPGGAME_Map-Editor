package com.rpggame.mapeditor.view.texteditor;

import com.badlogic.gdx.Gdx;
import com.rpggame.mapeditor.view.ImGuiView;
import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;

import java.util.ArrayList;
import java.util.List;

public class TextEditorView implements ImGuiView {
    private final List<TextFileEditorView> files;
    private TextFileEditorView selectedFile;


    public TextEditorView() {
        this.files = new ArrayList<>();
    }

    @Override
    public void imGui() {
        if (ImGui.begin("Text editor", ImGuiWindowFlags.MenuBar)) {
            if (ImGui.beginMenuBar()) {
                if (ImGui.beginMenu("File")) {
                    if (ImGui.menuItem("Save")) {
                        selectedFile.save();
                    }

                    ImGui.endMenu();
                }

                if (ImGui.beginMenu("Edit")) {
                    if (ImGui.menuItem("Undo", "Ctrl+Z")) {
                        selectedFile.undo();
                    }
                    if (ImGui.menuItem("Redo", "Ctrl+Y")) {
                        selectedFile.redo();
                    }

                    ImGui.separator();

                    if (ImGui.menuItem("Copy", "Ctrl+C")) {
                        selectedFile.copy();
                    }
                    if (ImGui.menuItem("Cut", "Ctrl+X")) {
                        selectedFile.cut();
                    }
                    if (ImGui.menuItem("Delete", "Del")) {
                        selectedFile.delete();
                    }
                    if (ImGui.menuItem("Paste", "Ctrl+V")) {
                        selectedFile.paste();
                    }

                    ImGui.endMenu();
                }

                ImGui.endMenuBar();
            }

            ImGui.beginChild("File editor");
            if (ImGui.beginTabBar("tabs")) {
                List<TextFileEditorView> closing = new ArrayList<>();

                for (TextFileEditorView file : this.files) {
                    ImBoolean isOpen = new ImBoolean(true);
                    boolean visible = ImGui.beginTabItem(file.getFileName(), isOpen, file.getTabItemFlags());

                    if (!isOpen.get()) {
                        closing.add(file);
                    }

                    if (visible) {
                        if (!file.equals(selectedFile)) {
                            selectedFile = file;
                        }
                        file.imGui();
                        ImGui.endTabItem();
                    }
                }

                for (TextFileEditorView file : closing) {
                    files.remove(file);
                }

                ImGui.endTabBar();
            }
            ImGui.endChild();

            if (ImGui.beginDragDropTarget()) {
                String fileHandle = ImGui.acceptDragDropPayload(String.class);
                if (fileHandle != null) {
                    long withSameName = this.files.stream()
                            .filter(file -> file.getFilePath().equals(fileHandle))
                            .count();

                    if (withSameName == 0) {
                        this.files.add(new TextFileEditorView(fileHandle));
                    }
                }
                ImGui.endDragDropTarget();
            }
        }
        ImGui.end();

    }

}
