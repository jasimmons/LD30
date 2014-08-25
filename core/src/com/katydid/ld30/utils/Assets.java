package com.katydid.ld30.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Assets {
	/************ SPRITES *************/
	// Trudy
	public static Sprite trudyFront, trudyBack, trudyFaceRight, trudyFaceLeft;
	// Trish
	public static Sprite trishFront, trishBack, trishFaceRight, trishFaceLeft;
	// Other
	public static Sprite selected;
	public static Sprite greenBar;
	public static Sprite healthBG;
	public static Sprite gameOver;
	public static Sprite[] numbers;
	
	// Desert
	public static Sprite d_background;
	public static Sprite d_cactus1;
	public static Sprite d_shrub1;
	public static Sprite d_rock1;
	public static Sprite d_cowSkull1;
	public static Sprite d_cowSkull2;
	public static Sprite d_tumbleweed;
	public static Sprite d_snake;
	public static Sprite d_wind;
	
	// Marsh / Swamp
	public static Sprite m_background;
	public static Sprite m_mushrooms1;
	public static Sprite m_oasis1;
	public static Sprite m_palmTree1;
	public static Sprite m_palmTree2;
	public static Sprite m_bird;

	/************* FONTS **************/
	public static String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
	public static BitmapFont defaultFont;
	
	/********** MUSIC & FX ************/
	//public static Sound ds_tumbleweed;

	public static void load() {
		// SPRITES
		gameOver = loadSprite(G.PATH + "images/gameover.png", 0, 0, G.WIDTH, G.HEIGHT, true);
		
		trudyFront = loadSprite(G.PATH + "images/trudy.png", 0, 0, 76, 112, true);
		trudyBack = loadSprite(G.PATH + "images/trudy.png", 76, 0, 76, 112, true);
		trudyFaceRight = loadSprite(G.PATH + "images/trudy.png", 152, 0, 76, 112, true);
		trudyFaceLeft = loadSprite(G.PATH + "images/trudy.png", 228, 0, 76, 112, true);
		
		trishFront = loadSprite(G.PATH + "images/trish.png", 0, 0, 76, 112, true);
		trishBack = loadSprite(G.PATH + "images/trish.png", 76, 0, 76, 112, true);
		trishFaceRight = loadSprite(G.PATH + "images/trish.png", 152, 0, 76, 112, true);
		trishFaceLeft = loadSprite(G.PATH + "images/trish.png", 228, 0, 76, 112, true);
		
		selected = loadSprite(G.PATH + "images/selectedSheet.png", 0, 0, 384, 128, false);
		greenBar = loadSprite(G.PATH + "images/greenBar.png", 0, 0, 1, 64, false);
		healthBG = loadSprite(G.PATH + "images/healthBG.png", 0, 0, 404, 36, true);

		numbers = new Sprite[10];
		for (int i=0; i<10; i++) {
			numbers[i] = loadSprite(G.PATH + "images/numbers.png", 16 * i, 0, 16, 24, true);
		}
		
		d_background = loadSprite(G.PATH + "images/d_background.png", 0, 0, 800, 900, true);
		d_cactus1 = loadSprite(G.PATH + "images/d_cactus1.png", 0, 0, 108, 120, true);
		d_shrub1 = loadSprite(G.PATH + "images/d_shrub1.png", 0, 0, 88, 60, true);
		d_rock1 = loadSprite(G.PATH + "images/d_rock1.png", 0, 0, 56, 36, true);
		d_cowSkull1 = loadSprite(G.PATH + "images/d_cowSkull1.png", 0, 0, 76, 56, true);
		d_cowSkull2 = loadSprite(G.PATH + "images/d_cowSkull2.png", 0, 0, 100, 60, true);
		d_tumbleweed = loadSprite(G.PATH + "images/d_tumbleweed.png", 0, 0, 96, 96, true);
		d_snake = loadSprite(G.PATH + "images/d_snake.png", 0, 0, 256, 64, false);
		d_wind = loadSprite(G.PATH + "images/d_wind.png", 0, 0, 540, 52, false);
		
		m_background = loadSprite(G.PATH + "images/m_background.png", 0, 0, 800, 900, true);
		m_mushrooms1 = loadSprite(G.PATH + "images/m_mushrooms1.png", 0, 0, 60, 38, true);
		m_oasis1 = loadSprite(G.PATH + "images/m_oasis1.png", 0, 0, 136, 60, true);
		m_palmTree1 = loadSprite(G.PATH + "images/m_palmtree1.png", 0, 0, 166, 162, true);
		m_palmTree2 = loadSprite(G.PATH + "images/m_palmtree2.png", 0, 0, 164, 162, true);
		m_bird = loadSprite(G.PATH + "images/m_bird.png", 0, 0, 480, 32, false);
		
		// AUDIO
		//ds_tumbleweed = loadSound(G.PATH + "sounds/d_tumbleweed.wav");
	}

	public static Sprite loadSprite(String name, int x, int y, int width, int height, boolean flipY) {
		Texture texture = new Texture(Gdx.files.internal(name));
		Sprite region = new Sprite(texture, x, y, width, height);
		if (flipY) region.flip(false, true);
		return region;
	}

	public static BitmapFont loadFont(String fontFile, String imageFile, boolean flipY) {
		FileHandle fontHandle = Gdx.files.internal(fontFile);
		FileHandle imageHandle = Gdx.files.internal(imageFile);
		return new BitmapFont(fontHandle, imageHandle, flipY);
	}

	public static Music loadMusic(String musicFile) {
		FileHandle musicHandle = Gdx.files.internal(musicFile);
		Music out = Gdx.audio.newMusic(musicHandle);
		out.setVolume(0.2f);
		return out;
	}

	public static Sound loadSound(String soundFile) {
		FileHandle soundHandle = Gdx.files.internal(soundFile);
		Sound out = Gdx.audio.newSound(soundHandle);
		return out;
	}
	
	public static Sprite clone(Sprite toBeCloned) {
		Sprite cloned = new Sprite(toBeCloned.getTexture(), (int)toBeCloned.getX(), (int)toBeCloned.getY(), (int)toBeCloned.getWidth(), (int)toBeCloned.getHeight());
		cloned.flip(false, true);
		return cloned;
	}
}