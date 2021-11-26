package com.rpggame.mapeditor.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.tile.Tile;
import com.rpggame.mapeditor.view.TileSelector;
import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.component.rendering.TileMapComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;
import com.rpggame.rpggame.system.EntitySystem;


public class TileMapEditingSystem extends EntitySystem {

    private final Viewport viewport;
    private final Selector<Tile> tileSelector;
    private final Selector<Entity> entitySelector;

    public TileMapEditingSystem(Viewport viewport, Selector<Entity> entitySelector, Selector<Tile> tileSelector) {
        super(new EntityFamily(TileMapComp.class));
        this.viewport = viewport;
        this.tileSelector = tileSelector;
        this.entitySelector = entitySelector;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            Entity entity = entitySelector.getSelected();

            if (entity == null || !entity.hasComponent(TileMapComp.class))
                return false;

            TileMapComp tileMapComp = entity.getComponent(TileMapComp.class);

            if (tileMapComp.getTiledMap() == null)
                return false;

            Vector2 worldPos = viewport.unproject(new Vector2(x, y));
            worldPos.mul(TransformComp.getCombinedMatrix(entity).inv());
            int tileX = (int) (worldPos.x) / tileMapComp.getTileWidth();
            int tileY = (int) (worldPos.y) / tileMapComp.getTileHeight();
            TiledMapTileLayer layer = (TiledMapTileLayer) tileMapComp.getTiledMap().getLayers().get(0);
            Tile tile = tileSelector.getSelected();
            if (tile != null) {
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                cell.setTile(new StaticTiledMapTile(tileMapComp.getSplitTiles()[tile.getSheetRow()][tile.getSheetColumn()]));
                layer.setCell(tileX, tileY, cell);
            }

            return true;
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            Entity entity = entitySelector.getSelected();

            if (entity == null || !entity.hasComponent(TileMapComp.class))
                return false;

            TileMapComp tileMapComp = entity.getComponent(TileMapComp.class);

            if (tileMapComp.getTiledMap() == null)
                return false;

            Vector2 worldPos = viewport.unproject(new Vector2(x, y));
            worldPos.mul(TransformComp.getCombinedMatrix(entity).inv());
            int tileX = (int) (worldPos.x) / tileMapComp.getTileWidth();
            int tileY = (int) (worldPos.y) / tileMapComp.getTileHeight();
            TiledMapTileLayer layer = (TiledMapTileLayer) tileMapComp.getTiledMap().getLayers().get(0);
            layer.setCell(tileX, tileY, null);

            return true;
        }

        return false;
    }
}
