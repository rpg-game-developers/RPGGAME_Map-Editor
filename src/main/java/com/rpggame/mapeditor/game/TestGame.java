package com.rpggame.mapeditor.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.tile.Tile;
import com.rpggame.rpggame.EntityApplicationAdapter;

public class TestGame extends EntityApplicationAdapter {
    private TiledMap tileMap;
    private Texture tiles;
    private TextureRegion[][] splitTiles;
    private Selector<Tile> tileSelector;
    private TiledMapRenderer renderer;

    public TestGame(Selector<Tile> tileSelector) {
        this.tileSelector = tileSelector;
    }

    @Override
    public void create() {
        tiles = new Texture(Gdx.files.internal("spriteAssets/testSpriteSheet.png"));
        splitTiles = TextureRegion.split(tiles, 17, 17);
        tileMap = new TiledMap();
        MapLayers layers = tileMap.getLayers();
        TiledMapTileLayer layer = new TiledMapTileLayer(150, 100, 16, 16);
        layers.add(layer);
        renderer = new OrthogonalTiledMapRenderer(tileMap);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        renderer.setView(camera);
        renderer.render();
    }

    @Override
    public void dispose() {
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
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            Vector2 worldPos = viewport.unproject(new Vector2(x, y));
            int tileX = (int) (worldPos.x) / 16;
            int tileY = (int) (worldPos.y) / 16;
            TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get(0);
            Tile tile = tileSelector.getSelected();
            if (tile != null) {
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                cell.setTile(new StaticTiledMapTile(splitTiles[tile.getSheetRow()][tile.getSheetColumn()]));
                layer.setCell(tileX, tileY, cell);
            }
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            Vector2 worldPos = viewport.unproject(new Vector2(x, y));
            int tileX = (int) (worldPos.x) / 16;
            int tileY = (int) (worldPos.y) / 16;
            TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get(0);
            layer.setCell(tileX, tileY, null);
        }

        return true;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        return false;
    }

    @Override
    public boolean scrolled(float deltaX, float deltaY) {
        return false;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

}
