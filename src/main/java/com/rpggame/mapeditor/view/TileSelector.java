package com.rpggame.mapeditor.view;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.tile.Tile;
import com.rpggame.rpggame.component.rendering.TileMapComp;
import com.rpggame.rpggame.entity.Entity;
import imgui.ImGui;
import imgui.ImVec2;

public class TileSelector implements ImGuiView {
    private final Selector<Tile> selector;
    private final Selector<Entity> entitySelector;

    public TileSelector(Selector<Tile> selector, Selector<Entity> entitySelector) {
        this.selector = selector;
        this.entitySelector = entitySelector;
    }

    @Override
    public void imGui() {
        if (!ImGui.begin("Tile selector")) {
            ImGui.end();
            return;
        }

        // default size
        if (ImGui.isWindowAppearing()) {
            ImGui.setWindowSize(400, 400);
        }

        Entity entity = entitySelector.getSelected();
        if (entity == null || !entity.hasComponent(TileMapComp.class)) {
            ImGui.text("Select an entity with a Tile Map Component.");
        } else {
            TileMapComp tileMap = entity.getComponent(TileMapComp.class);

            ImVec2 windowPos = new ImVec2();
            ImGui.getWindowPos(windowPos);
            ImVec2 windowSize = new ImVec2();
            ImGui.getWindowSize(windowSize);
            ImVec2 itemSpacing = new ImVec2();
            ImGui.getStyle().getItemSpacing(itemSpacing);
            float windowX2 = windowPos.x + windowSize.x;
            int rows = tileMap.getSheetRows();
            int columns = tileMap.getSheetColumns();
            for (int i=0; i<rows; i++) {
                for (int j=0; j<columns; j++) {
                    float spriteWidth = 32.0f;
                    float spriteHeight = 32.0f;
                    TextureRegion coords = tileMap.getSplitTiles()[i][j];

                    ImGui.pushID(i*columns + j);
                    if (ImGui.imageButton(tileMap.getSprite().getTextureObjectHandle(), spriteWidth, spriteHeight, coords.getU(), coords.getV(), coords.getU2(), coords.getV2(), 1)) {
                        selector.setSelected(new Tile(i, j));
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if((i+1 < rows || j+1 < columns) && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
            }
        }

        ImGui.end();
    }
}
