package com.katydid.ld30;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.katydid.ld30.utils.Assets;
import com.katydid.ld30.utils.Input;
import com.katydid.ld30.utils.Screen;

public class LD30 extends ApplicationAdapter {
	private Screen screen;
	private Input input;
	
	@Override
	public void create () {
		input = new Input();
		Gdx.input.setInputProcessor(input);
		
		Assets.load();
		
		// Whatever screen we have here is the first one that is shown when starting the game
		setScreen(new GameScreen());
	}

	// Automatically called by LibGDX every frame. Also tells the Screen to render
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (screen != null) screen.update();
	}
	
	// Change screens
	public void setScreen(Screen newScreen) {
		if (screen != null) screen.removed();
		screen = newScreen;
		if (screen != null) screen.init(this);
	}
	
	// Access the Input class to determine user inputs
	public Input getInput() {
		return input;
	}
	
	// Utility method for easily checking if a button was pressed
	public boolean isKeyPressed(int key) {
		return input.isPressed(key);
	}
}
