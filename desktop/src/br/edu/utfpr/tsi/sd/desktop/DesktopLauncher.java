package br.edu.utfpr.tsi.sd.desktop;

import br.edu.utfpr.tsi.sd.client.AsteroidsClientGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		var config = new LwjglApplicationConfiguration();
		new LwjglApplication(new AsteroidsClientGame(), config);
	}
}
