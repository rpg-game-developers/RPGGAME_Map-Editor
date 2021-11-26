package com.rpggame.mapeditor.view.menu;

import com.badlogic.gdx.physics.box2d.World;
import com.google.gson.JsonObject;
import com.rpggame.mapeditor.view.ImGuiView;
import com.rpggame.rpggame.EntityApplicationAdapter;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.repository.EntityAsJsonRepository;
import imgui.ImGui;

public class MenuBarView implements ImGuiView {

    private EntityApplicationAdapter game;
    private SaveSceneView saveScene;
    private OpenSceneView openScene;

    public MenuBarView(EntityApplicationAdapter game) {
        this.game = game;
        this.saveScene = new SaveSceneView(game);
        this.openScene = new OpenSceneView(game);
    }

    @Override
    public void imGui() {
        boolean shouldSaveScene = false;
        boolean shouldOpenScene = false;

        if (ImGui.beginMainMenuBar()) {
            if (ImGui.beginMenu("File")) {
                if (ImGui.menuItem("Save scene as...")) {
                    shouldSaveScene = true;
                }

                if (ImGui.menuItem("Open scene...")) {
                    shouldOpenScene = true;
                }

                ImGui.endMenu();
            }
            ImGui.endMainMenuBar();
        }

        if (shouldSaveScene)
            ImGui.openPopup("Save scene");
        saveScene.imGui();

        if (shouldOpenScene)
            ImGui.openPopup("Open scene");
        openScene.imGui();
    }
}
