package com.katydid.ld30;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.katydid.ld30.utils.Assets;

public class HealthBar {
	protected Sprite sprite = Assets.greenBar;
	public Vector2 position = new Vector2();
	public int percent = 100;
	protected float scale = 4f;		// number of pixels per percentage (if 2, 1% = 2px)
	
	public void render(SpriteBatch batch) {
		batch.draw(Assets.healthBG, position.x - 2, position.y - 2);
		if (percent < 0) percent = 0;
		batch.draw(sprite, position.x, position.y, percent * scale, 32);
	}
}
