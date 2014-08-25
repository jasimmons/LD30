package com.katydid.ld30;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.katydid.ld30.utils.Animation;
import com.katydid.ld30.utils.Assets;
import com.katydid.ld30.utils.G;
import com.katydid.ld30.utils.Input;
import com.katydid.ld30.utils.Screen;

public class GameScreen extends Screen {
	protected boolean blockInput = false;
	protected boolean gameOver = false;
	protected Selected selectedCharacter = Selected.trudy;
	protected HealthBar healthBar;
	
	Trudy trudy;
	Trish trish;
	DesertScene desertScene;
	MarshScene marshScene;
	
	protected Animation selectedChar;
	protected enum Selected {
		trudy, trish;
	}
	
	protected float counter = 0;
	
	public GameScreen() {
		desertScene = new DesertScene();
		marshScene = new MarshScene();
		
		trudy = new Trudy();
		trudy.setPosition(getRandomTrudyPosition());
		trish = new Trish();
		trish.setPosition(new Vector2(3 * G.WIDTH / 4, G.HEIGHT / 2));
		
		healthBar = new HealthBar();
		healthBar.position.x = G.WIDTH / 2 - 200;
		healthBar.position.y = 40;
		
		selectedChar = new Animation(Assets.selected, 96, 128, 4);
	}
	
	@Override
	public void render() {
		if (gameOver) {
			batch.draw(Assets.gameOver, 0, 0);
			drawScore(batch, - 1 * G.WIDTH + 40 / 2, -60);
		} else {
			desertScene.render(batch);
			marshScene.render(batch);
			trudy.render(batch);
			trish.render(batch);
			
			if (selectedCharacter == Selected.trudy) {
				Vector2 pos = new Vector2(
						trudy.getPosition().x + (trudy.getWidth() - (int)Assets.selected.getWidth() / 4) / 2,
						trudy.getPosition().y + (trudy.getHeight() - (int)Assets.selected.getHeight()) / 2);
				batch.draw(selectedChar.getCurrentFrame(), pos.x, pos.y);
			} else if (selectedCharacter == Selected.trish) {
				Vector2 pos = new Vector2(
						trish.getPosition().x + (trish.getWidth() - (int)Assets.selected.getWidth() / 4) / 2 ,
						trish.getPosition().y + (trish.getHeight() - (int)Assets.selected.getHeight()) / 2);
				batch.draw(selectedChar.getCurrentFrame(), pos.x, pos.y);
			}
			
			healthBar.render(batch);
			drawScore(batch, 0, 0);
		}
	}

	@Override
	public void debugRender() {
		desertScene.debugRender(debugRenderer);
		marshScene.debugRender(debugRenderer);
		trudy.debugRender(debugRenderer);
		trish.debugRender(debugRenderer);
	}

	@Override
	public void logic() {
		if (healthBar.percent > 0) {
			desertScene.update();
			marshScene.update();
			trudy.update();
			trish.update();
			
			handleInput();
			stayOnScreen();
			hitHurtfulObject();
			calcScore();
		} else {
			blockInput = true;
			gameOver = true;
		}
		
		if (selectedChar.isPaused()) {
			G.debug("SelectedChar is paused... resuming.");
			selectedChar.resume();
		}
	}

	protected void handleInput() {
		if (!blockInput) {
			if (keyPressed(Input.TAB)) {
				if (selectedCharacter == Selected.trish) selectedCharacter = Selected.trudy;
				else if (selectedCharacter == Selected.trudy) selectedCharacter = Selected.trish;
				game.getInput().releaseKey(Input.TAB);
			}
			
			if (keyPressed(Input.CTRL)) {
				healthBar.percent -= 1;
			}
			
			if (selectedCharacter == Selected.trudy) {
				Array<Character> desertObjects = desertScene.getAllObjects();
				
				if (keyPressed(Input.DPAD_LEFT)) {
					trudy.setFacingDirection(Character.FacingDirection.left);
					
					boolean doMove = true;
					Rectangle tempBounds = trudy.getBounds();
					tempBounds.x -= 10;
					for (Character obj : desertObjects) {
						if (obj.getBounds().overlaps(tempBounds)) doMove = false;
					}
					if (doMove) trudy.setPosition((new Vector2(trudy.getPosition().x - 10, trudy.getPosition().y)));
				} else if (keyPressed(Input.DPAD_RIGHT)) {
					trudy.setFacingDirection(Character.FacingDirection.right);
					
					boolean doMove = true;
					Rectangle tempBounds = trudy.getBounds();
					tempBounds.x += 10;
					for (Character obj : desertObjects) {
						if (obj.getBounds().overlaps(tempBounds)) doMove = false;
					}
					if (doMove) trudy.setPosition((new Vector2(trudy.getPosition().x + 10, trudy.getPosition().y)));
				} else if (keyPressed(Input.DPAD_UP)) {
					trudy.setFacingDirection(Character.FacingDirection.up);
					
					boolean doMove = true;
					Rectangle tempBounds = trudy.getBounds();
					tempBounds.y -= 10;
					for (Character obj : desertObjects) {
						if (obj.getBounds().overlaps(tempBounds)) doMove = false;
					}
					if (doMove) trudy.setPosition((new Vector2(trudy.getPosition().x, trudy.getPosition().y - 10)));
				} else if (keyPressed(Input.DPAD_DOWN)) {
					trudy.setFacingDirection(Character.FacingDirection.down);
					
					boolean doMove = true;
					Rectangle tempBounds = trudy.getBounds();
					tempBounds.y += 10;
					for (Character obj : desertObjects) {
						if (obj.getBounds().overlaps(tempBounds)) doMove = false;
					}
					if (doMove) trudy.setPosition((new Vector2(trudy.getPosition().x, trudy.getPosition().y + 10)));
				}
			} else if (selectedCharacter == Selected.trish) {
				Array<Character> marshObjects = marshScene.getAllObjects();
				
				if (keyPressed(Input.DPAD_LEFT)) {
					trish.setFacingDirection(Character.FacingDirection.left);
					
					boolean doMove = true;
					Rectangle tempBounds = trish.getBounds();
					tempBounds.x -= 10;
					for (Character obj : marshObjects) {
						if (obj.getBounds().overlaps(tempBounds)) doMove = false;
					}
					if (doMove) trish.setPosition((new Vector2(trish.getPosition().x - 10, trish.getPosition().y)));
				} else if (keyPressed(Input.DPAD_RIGHT)) {
					trish.setFacingDirection(Character.FacingDirection.right);
					
					boolean doMove = true;
					Rectangle tempBounds = trish.getBounds();
					tempBounds.x += 10;
					for (Character obj : marshObjects) {
						if (obj.getBounds().overlaps(tempBounds)) doMove = false;
					}
					if (doMove) trish.setPosition((new Vector2(trish.getPosition().x + 10, trish.getPosition().y)));
				} else if (keyPressed(Input.DPAD_UP)) {
					trish.setFacingDirection(Character.FacingDirection.up);
					
					boolean doMove = true;
					Rectangle tempBounds = trish.getBounds();
					tempBounds.y -= 10;
					for (Character obj : marshObjects) {
						if (obj.getBounds().overlaps(tempBounds)) doMove = false;
					}
					if (doMove) trish.setPosition((new Vector2(trish.getPosition().x, trish.getPosition().y - 10)));
				} else if (keyPressed(Input.DPAD_DOWN)) {
					trish.setFacingDirection(Character.FacingDirection.down);
					
					boolean doMove = true;
					Rectangle tempBounds = trish.getBounds();
					tempBounds.y += 10;
					for (Character obj : marshObjects) {
						if (obj.getBounds().overlaps(tempBounds)) doMove = false;
					}
					if (doMove) trish.setPosition((new Vector2(trish.getPosition().x, trish.getPosition().y + 10)));
				}
			}
		}
	}
	
	protected void stayOnScreen() {
		Vector2 trudyPos = trudy.getPosition();
		if (trudyPos.x < desertScene.bounds.x) trudyPos.x = desertScene.bounds.x;
		if (trudyPos.x + trudy.getWidth() > desertScene.bounds.width) trudyPos.x = desertScene.bounds.width - trudy.getWidth();
		if (trudyPos.y < desertScene.bounds.y) trudyPos.y = desertScene.bounds.y;
		if (trudyPos.y + trudy.getHeight() > desertScene.bounds.height) trudyPos.y = desertScene.bounds.height - trudy.getHeight();
		
		Vector2 trishPos = trish.getPosition();
		if (trishPos.x < marshScene.bounds.x) trishPos.x = marshScene.bounds.x;
		if (trishPos.x + trish.getWidth() > marshScene.bounds.x + marshScene.bounds.width) trishPos.x = marshScene.bounds.x + marshScene.bounds.width - trish.getWidth();
		if (trishPos.y < marshScene.bounds.y) trishPos.y = marshScene.bounds.y;
		if (trishPos.y + trish.getHeight() > marshScene.bounds.height) trishPos.y = marshScene.bounds.height - trish.getHeight();
	}
	
	protected boolean hitHurtfulObject() {
		Array<Character> objs = desertScene.getObjectsThatHurt();
		objs.addAll(marshScene.getObjectsThatHurt());
		for (Character obj : objs) {
			if (obj.getBounds().overlaps(trudy.getBounds()) || obj.getBounds().overlaps(trish.getBounds())) {
				obj.destroyable = true;
				healthBar.percent -= 20;
			}
		}
		return false;
	}
	
	protected Vector2 getRandomTrudyPosition() {
		G.debug("Trying to place Trudy, objects = " + desertScene.getAllObjects().size);
		Vector2 pos = new Vector2(G.WIDTH / 4, G.HEIGHT / 2);
		while (!desertScene.isValidTrudyPosition(pos)) {
			G.debug("(" + pos.x + "," + pos.y + ") is not a valid position for Trudy");
			pos.x = (int)(Math.random() * G.WIDTH / 2);
			pos.y = (int)(Math.random() * G.HEIGHT);
		}
		return pos;
	}
	
	protected void calcScore() {
		counter += Gdx.graphics.getDeltaTime();
		if (counter >= 1f) {
			counter = 0;
			G.score++;
		}
	}
	
	protected void drawScore(SpriteBatch batch, int offsetX, int offsetY) {
		String scoreStr = Integer.toString(G.score);
		char[] digits = scoreStr.toCharArray();
		for (int i=0; i<digits.length; i++) {
			batch.draw(Assets.numbers[java.lang.Character.getNumericValue(digits[i])], offsetX + G.WIDTH - 80 + i * 20, offsetY + 20);
		}
	}
}
