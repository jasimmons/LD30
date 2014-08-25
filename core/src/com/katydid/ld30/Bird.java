package com.katydid.ld30;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.katydid.ld30.utils.Animation;
import com.katydid.ld30.utils.Assets;
import com.katydid.ld30.utils.G;

public class Bird extends Character {
	protected Animation anim;
	protected Vector2 startPos;
	protected Vector2 endPos;

	public Bird() {
		anim = new Animation(Assets.m_bird, 120, 32, 4);
		anim.setFrameTime(50);
		
		hurts = true;
		
		int startPosX = G.WIDTH / 2 + (int)(Math.random() * G.WIDTH / 2);
		int endPosX = G.WIDTH / 2 + (int)(Math.random() * G.WIDTH / 2);
		int top = (int)(Math.random() * 2);
		int startPosY = (top == 0)? -100 : G.HEIGHT + 100;
		int endPosY = (top == 0)? G.HEIGHT + 100 : -100;
		startPos = new Vector2(startPosX, startPosY);
		endPos = new Vector2(endPosX, endPosY);
		
		position = startPos;
		setBoundingBox(120, 32);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		position.lerp(endPos, .01f);
		
		batch.draw(anim.getCurrentFrame(), position.x, position.y);
	}
}
