package com.katydid.ld30.utils;

import com.badlogic.gdx.Gdx;

public class G {
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;
	public static final String PATH = "assets/";
	public static boolean _WARN = true;
	public static boolean _DEBUG = true;
	public static boolean _INFO = true;
	public static boolean _DEBUG_RENDER = false;
	
	public static Mouse mouse = new Mouse();
	public static int score = 0;
	
	public static void warn(String msg)  { if (_WARN) 	Gdx.app.log("WARNING", msg); }
	public static void debug(String msg) { if (_DEBUG) 	Gdx.app.log("DEBUG", msg); }
	public static void info(String msg)  { if (_INFO) 	Gdx.app.log("INFO", msg); }
}
