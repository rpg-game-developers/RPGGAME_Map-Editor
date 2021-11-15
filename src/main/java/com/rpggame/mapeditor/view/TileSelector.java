package com.rpggame.mapeditor.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.rpggame.mapeditor.model.SpriteSheet;
import imgui.ImGui;
import imgui.ImVec2;
import org.lwjgl.Sys;

import java.awt.image.BufferedImage;

public class TileSelector {
    private SpriteSheet spriteSheet;

    public TileSelector() {
        this.spriteSheet = new SpriteSheet("spriteAssets/testSpriteSheet.png", 18, 27, 16, 1);
    }

    public void imGui() {
        ImGui.begin("Tile selector");

        ImVec2 windowPos = new ImVec2();
        ImGui.getWindowPos(windowPos);
        ImVec2 windowSize = new ImVec2();
        ImGui.getWindowSize(windowSize);
        ImVec2 itemSpacing = new ImVec2();
        ImGui.getStyle().getItemSpacing(itemSpacing);
        float windowX2 = windowPos.x + windowSize.x;

        int rows = spriteSheet.getRows();
        int columns = spriteSheet.getColumns();
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                float spriteWidth = 32.0f;
                float spriteHeight = 32.0f;
                Vector2[] coords = spriteSheet.getTileCoords(i, j);

                ImGui.pushID(i*columns + j);
                if (ImGui.imageButton(spriteSheet.getTextureID(), spriteWidth, spriteHeight, coords[0].x, coords[0].y, coords[1].x, coords[1].y, 1)) {
                    System.out.println("Button " + i + " " + j + " clicked");
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

        ImGui.end();
    }
}
