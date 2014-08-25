package com.katydid.ld30.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

public class Animation {
	private final float FRAME_RATE = 1/60f;
	private float frameTime = FRAME_RATE * 6;		// Default: 10 fps
	private Sprite[] frames;
	private int frameIndex = 0;
	private Timer timer;
	private boolean paused = false;
	private boolean reversed = false;
	public boolean loop = true;
	
	public Animation(TextureRegion spriteSheet, int frameWidth, int frameHeight) {
		TextureRegion[][] tmp = spriteSheet.split(frameWidth, frameHeight);
		frames = new Sprite[tmp[0].length];
		for (int i=0; i<tmp[0].length; i++) {
			frames[i] = new Sprite(tmp[0][i]);
		}
		
		timer = Timer.instance();
		timer.scheduleTask(new AnimTask(), 0, frameTime);
		timer.start();
	}
	
	public Animation(TextureRegion spriteSheet, int frameWidth, int frameHeight, int numFrames) {
		TextureRegion[][] tmp = spriteSheet.split(frameWidth, frameHeight);
		if (numFrames > 0 && numFrames <= tmp[0].length)
			frames = new Sprite[numFrames];
		else
			frames = new Sprite[tmp[0].length];
		for (int i=0; i<frames.length; i++) {
			frames[i] = new Sprite(tmp[0][i]);
		}
		
		timer = Timer.instance();
		timer.scheduleTask(new AnimTask(), 0, frameTime);
		timer.start();
	}
	
	public Sprite getCurrentFrame() {
		return frames[frameIndex];
	}
	
	public Sprite getFrameAtIndex(int index) {
		if (index >= 0 && index < frames.length)
			return frames[index];
		return null;
	}
	
	public int getFrameIndex() {
		return frameIndex;
	}
	
	public int getTotalFrames() {
		return frames.length;
	}
	
	public void setFrameTime(float ms) {
		this.frameTime = ms / 1000;
		timer.clear();
		timer.scheduleTask(new AnimTask(), 0, frameTime);
		timer.start();
	}
	
	public void pause() {
		timer.stop();
		paused = true;
	}
	
	public void resume() {
		timer.start();
		paused = false;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public boolean isReversed() {
		return reversed;
	}
	
	public void reverse() {
		reversed = !reversed;
	}
	
	class AnimTask extends Timer.Task {
		
		@Override
		public void run() {
			if (reversed) {
				frameIndex = (--frameIndex % frames.length);
				if (!loop && frameIndex == 0) pause();
			} else {
				frameIndex = (++frameIndex % frames.length);
				if (!loop && frameIndex == frames.length - 1) pause();
			}
		}
	}
}