package com.rpggame.mapeditor.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rpggame.mapeditor.game.TestGame;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.tile.Tile;
import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiWindowFlags;
import org.lwjgl.Sys;

import javax.swing.text.View;

public class GameView implements InputProcessor {

    private FrameBuffer fbo;
    private int lastScreenX = 0;
    private int lastScreenY = 0;
    private TestGame game;

    public GameView(TestGame game) {
        this.game = game;
        game.create();
    }

    public void draw() {
        fbo.begin();
        game.render();
        fbo.end();
    }

    public void imGui() {
        ImGui.begin("Game view", ImGuiWindowFlags.NoScrollbar | ImGuiWindowFlags.NoScrollWithMouse);

        ImVec2 windowPos = new ImVec2();
        ImGui.getCursorScreenPos(windowPos);
        windowPos.x -= ImGui.getScrollX();
        windowPos.y -= ImGui.getScrollY();

        ImVec2 windowSize = new ImVec2();
        ImGui.getContentRegionAvail(windowSize);
        windowSize.x -= ImGui.getScrollX();
        windowSize.y -= ImGui.getScrollY();

        if (windowSize.x > 0 && windowSize.y > 0) {
            Viewport viewport = game.getViewport();

            if (windowSize.x != viewport.getScreenWidth() || windowSize.y != viewport.getScreenHeight()) {
                if (fbo != null)
                    fbo.dispose();
                fbo = new FrameBuffer(Pixmap.Format.RGBA8888, (int)windowSize.x, (int)windowSize.y, false);
                viewport.setScreenWidth((int)windowSize.x);
                viewport.setScreenHeight((int)windowSize.y);
            }

            viewport.update((int)windowSize.x, (int)windowSize.y, false);
            viewport.setScreenX((int)windowPos.x);
            viewport.setScreenY(Gdx.graphics.getHeight() - (int)windowPos.y - (int)windowSize.y);

            draw();

            Texture texture = fbo.getColorBufferTexture();
            ImGui.image(texture.getTextureObjectHandle(), windowSize.x, windowSize.y, 0.0f, 1.0f, 1.0f ,0.0f);
        }

        ImGui.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (isMouseOutside(x, y))
            return false;

        lastScreenX = x;
        lastScreenY = y;

        touchDragged(x, y, pointer);

        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (isMouseOutside(x, y))
            return false;

        lastScreenX = x;
        lastScreenY = y;
        return true;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if (isMouseOutside(x, y))
            return false;

        if (Gdx.input.isButtonPressed(Input.Buttons.MIDDLE)) {
            Viewport viewport = game.getViewport();
            Vector2 oldPos = viewport.unproject(new Vector2(lastScreenX, lastScreenY));
            Vector2 newPos = viewport.unproject(new Vector2(x, y));

            Vector2 delta = new Vector2(oldPos);
            delta.sub(newPos);

            game.getCamera().translate(delta);
        } else {
            game.touchDragged(x, y, pointer);
        }

        lastScreenX = x;
        lastScreenY = y;

        return true;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        if (isMouseOutside(x, y))
            return false;

        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        if (isMouseOutside(x, y))
            return false;

        game.getCamera().zoom *= Math.pow(1.1, amountX);
        game.getCamera().zoom *= Math.pow(1.1, amountY);

        return true;
    }

    public boolean isMouseOutside(int x, int y) {
        Viewport viewport = game.getViewport();
        return (x < viewport.getScreenX()
                || y < Gdx.graphics.getHeight() - viewport.getScreenY() - viewport.getScreenHeight()
                || x > viewport.getScreenX() + viewport.getScreenWidth()
                || y > Gdx.graphics.getHeight() - viewport.getScreenY());
    }
}
