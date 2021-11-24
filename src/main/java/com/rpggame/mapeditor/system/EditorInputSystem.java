package com.rpggame.mapeditor.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.component.physics.collision.RectangleCollisionComp;
import com.rpggame.rpggame.component.rendering.SpriteComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;
import com.rpggame.rpggame.system.EntitySystem;
import imgui.ImGui;

public class EditorInputSystem extends EntitySystem {

    private final Viewport viewport;
    private Selector<Entity> entitySelector;
    private Selector<Entity> justSelected;
    private int lastScreenX = 0;
    private int lastScreenY = 0;

    public EditorInputSystem(Viewport viewport, Selector<Entity> entitySelector) {
        super(new EntityFamily(TransformComp.class));
        this.viewport = viewport;
        this.entitySelector = entitySelector;
        justSelected = new Selector<>();
    }

    @Override
    public void onRender() {
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        lastScreenX = x;
        lastScreenY = y;
        for (Entity entity : getEntities().descendingSet()) {
            Vector2 collision = new Vector2(0.0f, 0.0f);

            if (entity.hasComponent(SpriteComp.class)) {
                Texture sprite = entity.getComponent(SpriteComp.class).getSprite();
                if (sprite != null) {
                    collision.x = sprite.getWidth();
                    collision.y = sprite.getHeight();
                }
            } else if (entity.hasComponent(RectangleCollisionComp.class)) {
                collision = entity.getComponent(RectangleCollisionComp.class).getSize();
            }

            Vector2 mousePos = viewport.unproject(new Vector2(x, y));
            mousePos.mul(TransformComp.getCombinedMatrix(entity).inv());

            if (mousePos.x >= 0.0f && mousePos.y >= 0.0f && mousePos.x <= collision.x && mousePos.y <= collision.y) {
                entitySelector.setSelected(entity);
                justSelected.setSelected(entity);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        justSelected.resetSelected();
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        Entity selected = justSelected.getSelected();

        if (selected != null && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            Vector2 oldPos = this.viewport.unproject(new Vector2(lastScreenX, lastScreenY));
            Vector2 newPos = this.viewport.unproject(new Vector2(x, y));

            Vector2 delta = new Vector2(newPos);
            delta.sub(oldPos);

            TransformComp transform = selected.getComponent(TransformComp.class);
            transform.getPosition().add(delta);
        }

        lastScreenX = x;
        lastScreenY = y;
        return false;
    }
}
