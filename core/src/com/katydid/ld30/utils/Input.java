package com.katydid.ld30.utils;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class Input implements InputProcessor {
	// Keyboard
	public static final int SPACE = 0;
	public static final int DPAD_UP = 1;
	public static final int DPAD_DOWN = 2;
	public static final int DPAD_LEFT = 3;
	public static final int DPAD_RIGHT = 4;
	public static final int ENTER = 5;
	public static final int W = 6;
	public static final int A = 7;
	public static final int S = 8;
	public static final int D = 9;
	public static final int CTRL = 10;
	public static final int TAB = 11;

	public boolean[] buttons = new boolean[64];
	public boolean[] oldButtons = new boolean[64];
	
	public Vector2 mouse = new Vector2(-1,-1);
	public Vector2 oldMouse = new Vector2(-1,-1);
	public boolean leftClick = false;
	public boolean acceptInput = true;

	public void set(int key, boolean down) {
		int button = -1;

		if (key == Keys.SPACE) button = SPACE;
		if (key == Keys.DPAD_UP) button = DPAD_UP;
		if (key == Keys.DPAD_DOWN) button = DPAD_DOWN;
		if (key == Keys.DPAD_LEFT) button = DPAD_LEFT;
		if (key == Keys.DPAD_RIGHT) button = DPAD_RIGHT;
		if (key == Keys.ENTER) button = ENTER;
		if (key == Keys.W) button = W;
		if (key == Keys.A) button = A;
		if (key == Keys.S) button = S;
		if (key == Keys.D) button = D;
		if (key == Keys.CONTROL_LEFT) button = CTRL;
		if (key == Keys.TAB) button = TAB;


		if (button >= 0) buttons[button] = down;
	}

	public void tick() {
		for (int i=0; i<buttons.length; i++) {
			oldButtons[i] = buttons[i];
		}
		oldMouse = mouse;
	}

	public void releaseAllKeys() {
		for (int i=0; i<buttons.length; i++) {
			buttons[i] = false;
		}
	}
	
	public void releaseKey(int key) {
		try {
			buttons[key] = false;
		} catch (ArrayIndexOutOfBoundsException ex) {}
	}
	
	public void clearMouse() {
		mouse.x = -1;
		mouse.y = -1;
		oldMouse.x = mouse.x;
		oldMouse.y = mouse.y;
	}

	@Override
	public boolean keyDown(int keycode) {
		set(keycode, true);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		set(keycode, false);
		return false;
	}

	@Override
	public boolean keyTyped(char character) { return false; }

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		leftClick = true;
		G.mouse.down = true;
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		leftClick = false;
		G.mouse.down = false;
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (leftClick) {
			mouseMoved(x,y);
			System.out.println("Dragging LeftClick " + mouse.x + ", " + mouse.y);
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int x, int y) {
		oldMouse = mouse;
		if (x != mouse.x || y != mouse.y) {
			mouse.x = x;
			mouse.y = y;
			G.mouse.pos.x = x;
			G.mouse.pos.y = y;
			return true;
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) { return false; }

	public boolean isPressed(int key) {
		if (acceptInput)
			return (buttons[key] && !oldButtons[key]);
		return false;
	}
	
	public void blockInputFor(int ms) {
		acceptInput = false;
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				acceptInput = true;
			}
		}, ms);
	}
}