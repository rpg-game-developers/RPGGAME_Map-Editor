package com.rpggame.mapeditor.view.component.rendering;

import com.badlogic.gdx.Gdx;
import com.rpggame.mapeditor.view.component.ComponentView;
import com.rpggame.rpggame.component.rendering.TileMapComp;
import imgui.ImGui;
import imgui.flag.ImGuiDataType;
import imgui.type.ImInt;

public class TileMapCompView extends ComponentView<TileMapComp> {
    private final ImInt tileWidth;
    private final ImInt tileHeight;
    private final ImInt tileMargin;

    public TileMapCompView() {
        super(TileMapComp.class, TileMapComp::new, "Tile map");
        tileWidth = new ImInt();
        tileHeight = new ImInt();
        tileMargin = new ImInt();
    }

    @Override
    public void imGui() {
        tileWidth.set(comp.getTileWidth());
        tileHeight.set(comp.getTileHeight());
        tileMargin.set(comp.getMargin());
        ImGui.inputScalar("Tile width", ImGuiDataType.S32, tileWidth);
        ImGui.inputScalar("Tile height", ImGuiDataType.S32, tileHeight);
        ImGui.inputScalar("Tile margin", ImGuiDataType.S32, tileMargin);
        comp.setTileWidth(tileWidth.get());
        comp.setTileHeight(tileHeight.get());
        comp.setMargin(tileMargin.get());

        if (comp.getSprite() != null) {
            int width = comp.getSprite().getTextureData().getWidth();
            int height = comp.getSprite().getTextureData().getHeight();
            ImGui.text("Image width: " + width);
            ImGui.text("Image height: " + height);
            ImGui.image(comp.getSprite().getTextureObjectHandle(), width, height, 0, 0, 1, 1);
            createDropTarget();
        }

        ImGui.button("Load sprite");
        createDropTarget();
    }

    private void createDropTarget() {
        if (ImGui.beginDragDropTarget()) {
            String fileHandle = ImGui.acceptDragDropPayload(String.class);
            if (fileHandle != null) {
                comp.setSprite(Gdx.files.local(fileHandle));
            }

            ImGui.endDragDropTarget();
        }
    }
}
