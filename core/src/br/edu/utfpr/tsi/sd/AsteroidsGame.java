package br.edu.utfpr.tsi.sd;

import br.edu.utfpr.tsi.sd.core.container.BulletsContainer;
import br.edu.utfpr.tsi.sd.core.container.PlayersContainer;
import br.edu.utfpr.tsi.sd.core.controller.KeyboardControl;
import br.edu.utfpr.tsi.sd.core.controller.NoopControl;
import br.edu.utfpr.tsi.sd.core.manager.Collider;
import br.edu.utfpr.tsi.sd.core.manager.Respawner;
import br.edu.utfpr.tsi.sd.core.model.Arena;
import br.edu.utfpr.tsi.sd.core.model.Player;
import br.edu.utfpr.tsi.sd.core.view.ContainerRenderer;
import br.edu.utfpr.tsi.sd.core.view.PlayerRenderer;
import br.edu.utfpr.tsi.sd.core.view.VisibleRenderer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;

import java.util.UUID;

public class AsteroidsGame extends Game {
    public static final float WORLD_WIDTH = 800f;
    public static final float WORLD_HEIGHT = 600f;
    private Screen asteroids;

    @Override
    public void create() {
        var viewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT);
        var shapeRenderer =  new ShapeRenderer();

        var arena = new Arena(WORLD_WIDTH, WORLD_HEIGHT);

        var player1 = Player.builder()
                .id(UUID.randomUUID())
                .color(Color.WHITE)
                .controls(new KeyboardControl())
                .build();

        var player2 = Player.builder()
                .id(UUID.randomUUID())
                .color(Color.LIGHT_GRAY)
                .controls(new NoopControl())
                .build();

        var bulletsContainer = new BulletsContainer();
        var playersContainer = new PlayersContainer<>();

        playersContainer.add(player1);
        playersContainer.add(player2);

        var respawner = Respawner.builder()
                .playersContainer(playersContainer)
                .heightBound(WORLD_HEIGHT)
                .widthBound(WORLD_WIDTH)
                .build();

        var collider = new Collider<>(playersContainer, bulletsContainer);

        var bulletsRenderer = new ContainerRenderer<>(bulletsContainer, VisibleRenderer::new);
        var playersRenderer = new ContainerRenderer<>(playersContainer, PlayerRenderer::new);

        asteroids = AsteroidScreen.builder()
                .arena(arena)
                .bulletsContainer(bulletsContainer)
                .player(player1)
                .shapeRenderer(shapeRenderer)
                .playerRenderer(playersRenderer)
                .bulletsRenderer(bulletsRenderer)
                .collider(collider)
                .respawner(respawner)
                .viewport(viewport)
                .build();

        setScreen(asteroids);
    }

    @Override
    public void dispose() {
        asteroids.dispose();
    }
}
