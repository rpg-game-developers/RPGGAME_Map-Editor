package com.rpggame.mapeditor.view.filesystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.rpggame.mapeditor.view.ImGuiView;
import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;

public class DirectoryView implements ImGuiView {
    private final FileHandle fileHandle;
    private static final Texture folderTexture;
    private static final Texture fileTexture;
    private static final Texture javascriptTexture;
    private static final Texture imageTexture;
    private static final Texture jsonTexture;

    static {
        folderTexture = new Texture(Gdx.files.internal("icons/folder.png"));
        fileTexture = new Texture(Gdx.files.internal("icons/file.png"));
        javascriptTexture = new Texture(Gdx.files.internal("icons/javascript.png"));
        imageTexture = new Texture(Gdx.files.internal("icons/image-file.png"));
        jsonTexture = new Texture(Gdx.files.internal("icons/json.png"));
    }

    public DirectoryView(FileHandle fileHandle) {
        this.fileHandle = fileHandle;
    }

    @Override
    public void imGui() {
        if (!fileHandle.exists())
            return;

        if(fileHandle.isDirectory()) {
            boolean isOpen = ImGui.treeNodeEx("", ImGuiTreeNodeFlags.SpanFullWidth);
            ImGui.sameLine();
            ImGui.image(folderTexture.getTextureObjectHandle(), 16.0f, 16.0f);
            ImGui.sameLine();
            ImGui.text(fileHandle.name());
            if (isOpen) {
                FileHandle[] files = fileHandle.list();

                // display directories first
                int id = 0;
                for (FileHandle file : files) {
                    if (file.isDirectory()) {
                        ImGui.pushID(id++);
                        DirectoryView directoryView = new DirectoryView(file);
                        directoryView.imGui();
                        ImGui.popID();
                    }
                }

                // then display files
                for (FileHandle file : files) {
                    if (!file.isDirectory()) {
                        ImGui.pushID(id++);
                        DirectoryView directoryView = new DirectoryView(file);
                        directoryView.imGui();
                        ImGui.popID();
                    }
                }

                ImGui.treePop();
            }
        } else {
            boolean isOpen = ImGui.treeNodeEx("", ImGuiTreeNodeFlags.Leaf | ImGuiTreeNodeFlags.SpanFullWidth);
            if (isOpen) {
                if (ImGui.beginDragDropSource()) {
                    ImGui.setDragDropPayload(fileHandle.path());
                    ImGui.text(fileHandle.name());
                    ImGui.endDragDropSource();
                }
                ImGui.treePop();
            }

            int texture = fileTexture.getTextureObjectHandle();
            String extension = fileHandle.extension();
            if (extension.equals("js")) {
                texture = javascriptTexture.getTextureObjectHandle();
            }
            if (extension.equals("png")) {
                texture = imageTexture.getTextureObjectHandle();
            }
            if (extension.equals("json")) {
                texture = jsonTexture.getTextureObjectHandle();
            }

            ImGui.sameLine();
            ImGui.image(texture, 16.0f, 16.0f);
            ImGui.sameLine();
            ImGui.text(fileHandle.name());
        }
    }
}
