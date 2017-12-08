package com.kolko.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kolko.game.Application;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Application.HEIGHT;
		config.width = Application.WIDTH;
		config.title = Application.TITLE;
		new LwjglApplication(new Application(), config);
	}
}
