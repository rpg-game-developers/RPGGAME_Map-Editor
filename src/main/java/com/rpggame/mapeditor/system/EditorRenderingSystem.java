package com.rpggame.mapeditor.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.component.physics.collision.CollisionComp;
import com.rpggame.rpggame.component.physics.collision.RectangleCollisionComp;
import com.rpggame.rpggame.component.rendering.SpriteComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.system.RenderingSystem;

public class EditorRenderingSystem extends RenderingSystem {

    private static ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final Selector<Entity> entitySelector;

    public EditorRenderingSystem(OrthographicCamera camera, SpriteBatch batch, Selector<Entity> entitySelector) {
        super(camera, batch);
        this.entitySelector = entitySelector;
    }

    public void drawLine(Vector2 start, Vector2 end, int lineWidth, Color color, Matrix4 projectionMatrix) {
        Gdx.gl.glLineWidth(lineWidth);
        shapeRenderer.setProjectionMatrix(projectionMatrix);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(color);
        shapeRenderer.line(start, end);
        shapeRenderer.end();
        Gdx.gl.glLineWidth(1);
    }

    @Override
    public void onRender() {
        drawLine(new Vector2(-1000000000,0), new Vector2(1000000000, 0), 1, Color.RED, camera.combined);
        drawLine(new Vector2(0,-1000000000), new Vector2(0, 1000000000), 1, Color.GREEN, camera.combined);
        super.onRender();

        Entity entity = entitySelector.getSelected();
        if (entity != null && entity.hasComponent(TransformComp.class)) {
            Vector2 size = new Vector2(0.0f, 0.0f);

            if (entity.hasComponent(SpriteComp.class)) {
                Texture sprite = entity.getComponent(SpriteComp.class).getSprite();
                if (sprite != null) {
                    size.x = sprite.getWidth();
                    size.y = sprite.getHeight();
                }
            } else if (entity.hasComponent(RectangleCollisionComp.class)) {
                size = entity.getComponent(RectangleCollisionComp.class).getSize();
            }

            Matrix4 projection = new Matrix4();
            projection.set(TransformComp.getCombinedMatrix(entity));
            projection.mulLeft(camera.combined);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.setProjectionMatrix(projection);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(new Color(1.0f, 1.0f, 1.0f, 0.3f));
            shapeRenderer.rect(0.0f, 0.0f, size.x, size.y);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
    }

}
