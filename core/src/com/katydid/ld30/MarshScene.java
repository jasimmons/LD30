package com.katydid.ld30;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.katydid.ld30.utils.Assets;
import com.katydid.ld30.utils.G;

public class MarshScene {
	protected Array<Character> objects;
	protected int oasisDensity = 4;
	protected int mushroomsDensity = 4;
	protected int palmTreeDensity = 6;
	
	protected float counter = 0;
	protected float birdTimer = 2.5f;
	
	public Rectangle bounds;
	
	public MarshScene() {
		objects = new Array<Character>();
		
		bounds = new Rectangle(G.WIDTH / 2, 0, G.WIDTH / 2, G.HEIGHT);
		
		populate();
		
		objects.add(new Bird());
	}
	
	protected void populate() {
		for (int i=0; i<oasisDensity; i++) {
			int x = G.WIDTH / 2 + (int)(Math.random() * G.WIDTH / 2 - 100);
			if (x < G.WIDTH / 2) x = G.WIDTH / 2 + (int)(Math.random() * G.WIDTH / 4);
			int y = (int)(Math.random() * G.HEIGHT - 100);
			if (y < 0) y = (int)(Math.random() * G.HEIGHT / 4);
			Character oasis = new Character();
			oasis.setSprite(Assets.m_oasis1);
			oasis.setPosition(new Vector2(x, y));
			oasis.setBoundingBox((int)Assets.m_oasis1.getWidth(), (int)Assets.m_oasis1.getHeight());
			objects.add(oasis);
		}
		
		for (int i=0; i<mushroomsDensity; i++) {
			int x = G.WIDTH / 2 + (int)(Math.random() * G.WIDTH / 2 - 100);
			if (x < G.WIDTH / 2) x = G.WIDTH / 2 + (int)(Math.random() * G.WIDTH / 4);
			int y = (int)(Math.random() * G.HEIGHT - 100);
			if (y < 0) y = (int)(Math.random() * G.HEIGHT / 4);
			Character mushrooms = new Character();
			mushrooms.setSprite(Assets.m_mushrooms1);
			mushrooms.setPosition(new Vector2(x, y));
			mushrooms.setBoundingBox((int)Assets.m_mushrooms1.getWidth(), (int)Assets.m_mushrooms1.getHeight());
			objects.add(mushrooms);
		}
		
		for (int i=0; i<palmTreeDensity; i++) {
			int x = G.WIDTH / 2 + (int)(Math.random() * G.WIDTH / 2 - 100);
			if (x < G.WIDTH / 2) x = G.WIDTH / 2 + (int)(Math.random() * G.WIDTH / 4);
			int y = (int)(Math.random() * G.HEIGHT - 100);
			if (y < 0) y = (int)(Math.random() * G.HEIGHT / 4);
			Character palmTree = new Character();
			if (i % 2 == 0) {
				palmTree.setSprite(Assets.m_palmTree1);
				palmTree.setPosition(new Vector2(x, y));
				palmTree.setBoundingBox((int)Assets.m_palmTree1.getWidth(), (int)Assets.m_palmTree1.getHeight());
			} else {
				palmTree.setSprite(Assets.m_palmTree2);
				palmTree.setPosition(new Vector2(x, y));
				palmTree.setBoundingBox((int)Assets.m_palmTree2.getWidth(), (int)Assets.m_palmTree2.getHeight());
			}
			objects.add(palmTree);
		}
	}
	
	protected void makeBird() {
		Bird b = new Bird();
		objects.add(b);
	}
	
	public Array<Character> getAllObjects() {
		return objects;
	}
	
	public Array<Character> getObjectsThatHurt() {
		Array<Character> objectsThatHurt = new Array<Character>();
		for (int i=0; i<objects.size; i++) {
			if (objects.get(i).hurts)
				objectsThatHurt.add(objects.get(i));
		}
		return objectsThatHurt;
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(Assets.m_background, G.WIDTH / 2, 0);
		
		for (Character obj : objects)
			obj.render(batch);
	}
	
	public void debugRender(ShapeRenderer debugRenderer) {
		debugRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
		for (Character obj : objects)
			obj.debugRender(debugRenderer);
	}
	
	public void update() {
		counter += Gdx.graphics.getDeltaTime();
		if (counter >= birdTimer) {
			counter = 0;
			makeBird();
		}
		
		for (Character obj : objects) {
			obj.update();
			if (obj.destroyable || (obj.getPosition().x + obj.getWidth()) > G.WIDTH)
				objects.removeValue(obj, false);
		}
	}
}
