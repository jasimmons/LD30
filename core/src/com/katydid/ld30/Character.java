package com.katydid.ld30;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.katydid.ld30.utils.Assets;

public class Character {
	protected Sprite sprite;
	protected Vector2 position;
	protected Rectangle boundingBox;
	protected FacingDirection facingDir = FacingDirection.down;
	public boolean hurts = false;
	public boolean destroyable = false; 
	
	protected enum FacingDirection {
		up, down, left, right;
	}
	
	public Character() {
		this.sprite = Assets.trudyFront;
		this.position = new Vector2(0,0);
		this.boundingBox = new Rectangle(position.x, position.y, sprite.getWidth(), sprite.getHeight());
	}
	public void setFacingDirection(FacingDirection facingDir) { this.facingDir = facingDir; }
	public void setSprite(Sprite sprite) { this.sprite = sprite; }
	public void setPosition(Vector2 position) { this.position = position; }
	public void setBoundingBox(int width, int height) { this.boundingBox.width = width; this.boundingBox.height = height; }
	public Vector2 getPosition() { return position; }
	public Rectangle getBounds() { return boundingBox; }
	public int getWidth() { return (int)sprite.getWidth(); }
	public int getHeight() { return (int)sprite.getHeight(); }
	
	public void render(SpriteBatch batch) {
		if (sprite != null) batch.draw(sprite, position.x, position.y, sprite.getWidth(), sprite.getHeight());
	}
	
	public void debugRender(ShapeRenderer debugRenderer) {
		if (boundingBox != null) debugRenderer.rect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
	}
	
	public void update() {
		if (boundingBox != null) {
			boundingBox.setX(position.x);
			boundingBox.setY(position.y);
		}
	}
}
