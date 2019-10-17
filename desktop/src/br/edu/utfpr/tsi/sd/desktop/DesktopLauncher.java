package br.edu.utfpr.tsi.sd.desktop;

import br.edu.utfpr.tsi.sd.AsteroidsGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new AsteroidsGame(), config);
	}
}
