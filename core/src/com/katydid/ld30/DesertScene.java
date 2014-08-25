package com.katydid.ld30;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.katydid.ld30.utils.Animation;
import com.katydid.ld30.utils.Assets;
import com.katydid.ld30.utils.G;

public class DesertScene {
	public Animation wind;
	protected Vector2 windPos;
	protected Array<Character> objects;
	protected int cactusDensity = 4;
	protected int shrubDensity = 4;
	protected int cowSkullDensity = 2;
	protected int rockDensity = 3;
	
	protected float counter;
	protected float tumbleweedTimer = 2f;
	
	public Rectangle bounds;
	
	public DesertScene() {
		objects = new Array<Character>();
		
		wind = new Animation(Assets.d_wind, 135, 52, 4);
		wind.loop = false;
		wind.setFrameTime(250);
		windPos = new Vector2((int)(Math.random() * G.WIDTH - 128), (int)(Math.random() * G.HEIGHT));
		
		bounds = new Rectangle(0, 0, G.WIDTH / 2, G.HEIGHT);
		
		populate();
	}
	
	public boolean isValidTrudyPosition(Vector2 pos) {
		boolean valid = true;
		if (	!bounds.contains(pos.x, pos.y)) //||
				//bounds.contains(pos.x + Assets.trudyFront.getWidth(), pos.y) ||
				//bounds.contains(pos.x, pos.y + Assets.trudyFront.getHeight()) ||
				//bounds.contains(pos.x + Assets.trudyFront.getWidth(), pos.y + Assets.trudyFront.getHeight()))
		{
			valid = false;
		}
			
		for (Character obj : objects) {
			if (obj.getBounds().contains(pos.x, pos.y)) { valid = false; break; }
			//else if (!obj.getBounds().contains(pos.x + Assets.trudyFront.getWidth(), pos.y)) { valid = true; break; }
			//else if (!obj.getBounds().contains(pos.x, pos.y - Assets.trudyFront.getHeight())) { valid = true; break; }
			//else if (!obj.getBounds().contains(pos.x + Assets.trudyFront.getWidth(), pos.y - Assets.trudyFront.getHeight())) { valid = true; break; }
		}
		return valid;
	}
	
	protected void populate() {
		for (int i=0; i<cactusDensity; i++) {
			int x = (int)(Math.random() * G.WIDTH / 2 - 100);
			if (x < 0) x = (int)(Math.random() * G.WIDTH / 4);
			int y = (int)(Math.random() * G.HEIGHT - 100);
			if (y < 0) y = (int)(Math.random() * G.HEIGHT / 4);
			Character cactus = new Character();
			cactus.setSprite(Assets.d_cactus1);
			cactus.setPosition(new Vector2(x, y));
			cactus.setBoundingBox((int)Assets.d_cactus1.getWidth() - 20, (int)Assets.d_cactus1.getHeight());
			objects.add(cactus);
		}
		
		for (int i=0; i<shrubDensity; i++) {
			int x = (int)(Math.random() * G.WIDTH / 2 - 100);
			if (x < 0) x = (int)(Math.random() * G.WIDTH / 4);
			int y = (int)(Math.random() * G.HEIGHT - 100);
			if (y < 0) y = (int)(Math.random() * G.HEIGHT / 4);
			Character shrub = new Character();
			shrub.setSprite(Assets.d_shrub1);
			shrub.setPosition(new Vector2(x, y));
			shrub.setBoundingBox((int)Assets.d_shrub1.getWidth(), (int)Assets.d_shrub1.getHeight());
			objects.add(shrub);
		}
		
		for (int i=0; i<cowSkullDensity; i++) {
			int x = (int)(Math.random() * G.WIDTH / 2 - 100);
			if (x < 0) x = (int)(Math.random() * G.WIDTH / 4);
			int y = (int)(Math.random() * G.HEIGHT - 100);
			if (y < 0) y = (int)(Math.random() * G.HEIGHT / 4);
			Character cowSkull = new Character();
			if (i % 2 == 0) {
				cowSkull.setSprite(Assets.d_cowSkull1);
				cowSkull.setPosition(new Vector2(x, y));
				cowSkull.setBoundingBox((int)Assets.d_cowSkull1.getWidth(), (int)Assets.d_cowSkull1.getHeight());
			} else {
				cowSkull.setSprite(Assets.d_cowSkull2);
				cowSkull.setPosition(new Vector2(x, y));
				cowSkull.setBoundingBox((int)Assets.d_cowSkull2.getWidth(), (int)Assets.d_cowSkull2.getHeight());
			}
			objects.add(cowSkull);
		}
		
		for (int i=0; i<rockDensity; i++) {
			int x = (int)(Math.random() * G.WIDTH / 2 - 100);
			if (x < 0) x = (int)(Math.random() * G.WIDTH / 4);
			int y = (int)(Math.random() * G.HEIGHT - 100);
			if (y < 0) y = (int)(Math.random() * G.HEIGHT / 4);
			Character rock = new Character();
			rock.setSprite(Assets.d_rock1);
			rock.setPosition(new Vector2(x, y));
			rock.setBoundingBox((int)Assets.d_rock1.getWidth(), (int)Assets.d_rock1.getHeight());
			objects.add(rock);
		}
	}
	
	protected void makeTumbleweed() {
		Tumbleweed t = new Tumbleweed();
		int y = (int)(Math.random() * G.HEIGHT - 100);
		if (y < 0) y = (int)(Math.random() * G.HEIGHT / 4);
		t.setPosition(new Vector2(-200, y));
		objects.add(t);
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
		batch.draw(Assets.d_background, 0, 0);
		
		for (Character obj : objects)
			obj.render(batch);
		
		batch.draw(wind.getCurrentFrame(), windPos.x, windPos.y);
	}
	
	public void debugRender(ShapeRenderer debugRenderer) {
		debugRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
		for (Character obj : objects)
			obj.debugRender(debugRenderer);
	}
	
	public void update() {
		counter += Gdx.graphics.getDeltaTime();
		if (counter >= tumbleweedTimer) {
			counter = 0;
			makeTumbleweed();
		}
		
		for (Character obj : objects) {
			obj.update();
			if (obj.destroyable || (obj.getPosition().x + obj.getWidth()) > G.WIDTH / 2)
				objects.removeValue(obj, false);
		}
		
		if (wind.isPaused()) {
			wind.resume();
			windPos = new Vector2((int)(Math.random() * G.WIDTH / 2 - 128), (int)(Math.random() * G.HEIGHT));
		}
	}
}
