package com.rpggame.mapeditor;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.rpggame.mapeditor.view.Application;

public class RpgGameMapEditorStandalone {

	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setIdleFPS(60);
		config.useVsync(true);

		config.setTitle("Rpg Game");
		config.setWindowedMode(960, 640);
		new Lwjgl3Application(new Application(), config);
	}

}
