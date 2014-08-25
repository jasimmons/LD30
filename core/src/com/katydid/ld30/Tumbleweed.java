package com.katydid.ld30;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.katydid.ld30.utils.Assets;

public class Tumbleweed extends Character {
	protected int rotation = 0;
	protected boolean bouncingUp = false;
	protected int bounceValue = 0;
	protected int bounceHeight = 20;
	
	
	public Tumbleweed() {
		sprite = Assets.d_tumbleweed;
		hurts = true;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		rotation += 3;
		if (rotation >= 360) rotation = 0;
		position.x += 3;
		if (bouncingUp) {
			bounceValue += 2;
			if (bounceValue >= bounceHeight) bouncingUp = false;
		} else {
			bounceValue -= 2;
			if (bounceValue < 0) bouncingUp = true;
		}
		batch.draw(sprite, position.x, position.y + bounceValue, sprite.getWidth() / 2, sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight(), 1, 1, rotation);
	}
}
