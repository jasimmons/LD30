package com.katydid.ld30;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.katydid.ld30.utils.Assets;

public class Trudy extends Character {

	@Override
	public void render(SpriteBatch batch) {
		switch (facingDir) {
			case up: sprite = Assets.trudyBack; break;
			case down: sprite = Assets.trudyFront; break;
			case left: sprite = Assets.trudyFaceLeft; break;
			case right: sprite = Assets.trudyFaceRight; break;
			default: sprite = Assets.trudyFront; break;
		}
		batch.draw(sprite, position.x, position.y, sprite.getWidth(), sprite.getHeight());
	}
}
