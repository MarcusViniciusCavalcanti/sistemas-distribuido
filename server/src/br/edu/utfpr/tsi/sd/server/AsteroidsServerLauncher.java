package br.edu.utfpr.tsi.sd.server;

import com.badlogic.gdx.backends.headless.HeadlessApplication;

public class AsteroidsServerLauncher {
    public static void main(String[] args) {
        new HeadlessApplication(new AsteroidsServerGame());
    }
}
