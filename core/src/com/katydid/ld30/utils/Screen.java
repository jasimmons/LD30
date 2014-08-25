package com.katydid.ld30.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.katydid.ld30.LD30;

public abstract class Screen {
    protected LD30 game;
    protected SpriteBatch batch;
    protected ShapeRenderer debugRenderer;
    protected Rectangle bounds;
    
    public void removed() {
            batch.dispose();
    }
    
    public final void init(LD30 game) {
            this.game = game;
            bounds = new Rectangle(0, 0, G.WIDTH, G.HEIGHT);
            
            Matrix4 projection = new Matrix4();
            projection.setToOrtho(0, G.WIDTH, G.HEIGHT, 0, -1, 1);
            batch = new SpriteBatch();
            batch.setProjectionMatrix(projection);
            debugRenderer = new ShapeRenderer();
            debugRenderer.setProjectionMatrix(projection);
    }
    
    protected void setScreen(Screen screen) {
            game.setScreen(screen);
    }
    
    public abstract void render();
    public abstract void debugRender();
    public abstract void logic();
    
    public void update() {
    	batch.begin();
    	render();
    	batch.end();
    	if (G._DEBUG_RENDER) {
    		debugRenderer.begin(ShapeType.Line);
    		debugRender();
    		if (bounds != null) debugRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    		debugRenderer.end();
    		//drawDebugData();
    	}
    	
    	logic();
    	
    }
    
    public boolean keyPressed(int key) {
            return (game.getInput().buttons[key] && !game.getInput().oldButtons[key]);
    }
    
    protected void drawDebugData() {
    }
}