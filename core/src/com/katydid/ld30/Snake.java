package com.katydid.ld30;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.katydid.ld30.utils.Animation;
import com.katydid.ld30.utils.Assets;

public class Snake extends Character {
	protected Animation anim;
	protected Vector2 facingDir;
	protected int rotation = 0;
	
	public Snake() {
		anim = new Animation(Assets.d_snake, 64, 64, 4);
		facingDir = new Vector2(1, 1);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		facingDir.x += Math.random() / 10;
		facingDir.y += Math.random() / 10;
		rotation = (int)Math.atan2(facingDir.x, facingDir.y);
		position.x += facingDir.x;
		position.y += facingDir.y;
		batch.draw(anim.getCurrentFrame(), position.x, position.y, anim.getCurrentFrame().getWidth() / 2, anim.getCurrentFrame().getHeight() / 2, anim.getCurrentFrame().getWidth(), anim.getCurrentFrame().getHeight(), 1, 1, rotation);
	}

}
