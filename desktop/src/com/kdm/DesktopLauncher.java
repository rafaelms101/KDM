package com.kdm;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "KDM";
		config.width = 3440;
		config.height = 1440;
		config.fullscreen = true;
		new LwjglApplication(new KDM(), config);
	}
}
