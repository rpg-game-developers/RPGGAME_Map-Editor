package com.rpggame.mapeditor.view.menu;

import com.google.gson.JsonObject;
import com.rpggame.mapeditor.view.ImGuiView;
import com.rpggame.rpggame.EntityApplicationAdapter;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.repository.EntityAsJsonRepository;
import imgui.ImGui;
import imgui.type.ImString;

public class OpenSceneView implements ImGuiView {

    private EntityApplicationAdapter game;
    private ImString filePath;

    public OpenSceneView(EntityApplicationAdapter game) {
        this.game = game;
        this.filePath = new ImString("json/entity/entity.json");
    }

    @Override
    public void imGui() {
        if (ImGui.beginPopupModal("Open scene")) {
            if (ImGui.isWindowAppearing()) {
                ImGui.setWindowSize(400, 200);
            }

            ImGui.inputText("File path", this.filePath);

            if (ImGui.button("Ok")) {
                JsonObject json = EntityAsJsonRepository.loadJsonObjectFromFile(this.filePath.get());
                Entity loadedEntity = EntityAsJsonRepository.loadEntity(json);
                game.getEntityWorld().setRoot(loadedEntity);
                ImGui.closeCurrentPopup();
            }
            ImGui.sameLine();
            if (ImGui.button("Cancel")) {
                ImGui.closeCurrentPopup();
            }

            ImGui.endPopup();
        }
    }
}
