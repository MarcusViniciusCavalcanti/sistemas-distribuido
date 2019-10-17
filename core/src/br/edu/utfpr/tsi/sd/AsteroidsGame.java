package br.edu.utfpr.tsi.sd;

import br.edu.utfpr.tsi.sd.container.BulletsContainer;
import br.edu.utfpr.tsi.sd.container.Container;
import br.edu.utfpr.tsi.sd.controller.KeyboardControl;
import br.edu.utfpr.tsi.sd.model.Arena;
import br.edu.utfpr.tsi.sd.model.Bullet;
import br.edu.utfpr.tsi.sd.model.Player;
import br.edu.utfpr.tsi.sd.model.Ship;
import br.edu.utfpr.tsi.sd.view.ContainerRenderer;
import br.edu.utfpr.tsi.sd.view.PlayerRenderer;
import br.edu.utfpr.tsi.sd.view.VisibleRenderer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class AsteroidsGame extends Game {
    public static final float WORLD_WIDTH = 800f;
    public static final float WORLD_HEIGHT = 600f;
    private Screen asteroids;

    @Override
    public void create() {
        var viewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT);
        var shapeRenderer =  new ShapeRenderer();

        var arena = new Arena(WORLD_WIDTH, WORLD_HEIGHT);
        var player = new Player(new KeyboardControl(), Color.WHITE);

        var bulletsContainer = new BulletsContainer();
        player.setShip(new Ship(player));

        var playerRenderer = new PlayerRenderer(player);
        var bulletsRenderer = new ContainerRenderer<>(bulletsContainer, VisibleRenderer::new);

        asteroids =  AsteroidScreenBuilder.anAsteroidScreen()
                .withViewport(viewport)
                .withShapeRenderer(shapeRenderer)
                .withArena(arena)
                .withPlayer(player)
                .withBulletsContainer(bulletsContainer)
                .withBulletsRenderer(bulletsRenderer)
                .withPlayerRenderer(playerRenderer)
                .build();

        setScreen(asteroids);
    }

    @Override
    public void dispose() {
        asteroids.dispose();
    }
}
