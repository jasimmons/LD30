package com.katydid.ld30.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.katydid.ld30.LD30;
import com.katydid.ld30.utils.G;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = G.WIDTH;
		config.height = G.HEIGHT;
		new LwjglApplication(new LD30(), config);
	}
}
