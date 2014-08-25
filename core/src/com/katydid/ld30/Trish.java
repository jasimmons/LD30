package com.katydid.ld30;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.katydid.ld30.utils.Assets;

public class Trish extends Character {

	@Override
	public void render(SpriteBatch batch) {
		switch (facingDir) {
			case up: sprite = Assets.trishBack; break;
			case down: sprite = Assets.trishFront; break;
			case left: sprite = Assets.trishFaceLeft; break;
			case right: sprite = Assets.trishFaceRight; break;
			default: sprite = Assets.trishFront; break;
		}
		batch.draw(sprite, position.x, position.y, sprite.getWidth(), sprite.getHeight());
	}
}
