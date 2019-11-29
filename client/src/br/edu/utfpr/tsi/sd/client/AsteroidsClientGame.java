package br.edu.utfpr.tsi.sd.client;

import br.edu.utfpr.tsi.sd.client.connection.SocketIoClient;
import br.edu.utfpr.tsi.sd.client.connection.synchronization.LocalStateSynchronizer;
import br.edu.utfpr.tsi.sd.client.controller.KeyboardControls;
import br.edu.utfpr.tsi.sd.client.view.ContainerRenderer;
import br.edu.utfpr.tsi.sd.client.view.PlayerRenderer;
import br.edu.utfpr.tsi.sd.client.view.VisibleRenderer;
import br.edu.utfpr.tsi.sd.core.container.BulletsContainer;
import br.edu.utfpr.tsi.sd.core.container.PlayersContainer;
import br.edu.utfpr.tsi.sd.core.model.Arena;
import br.edu.utfpr.tsi.sd.core.tools.Delay;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;

import java.util.concurrent.locks.ReentrantLock;

import static br.edu.utfpr.tsi.sd.AsteroidsGame.WORLD_HEIGHT;
import static br.edu.utfpr.tsi.sd.AsteroidsGame.WORLD_WIDTH;

public class AsteroidsClientGame extends Game {
    private Screen asteroids;

    @Override
    public void create() {
        var viewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT);
        var shapeRenderer =  new ShapeRenderer();
        var localControls = new KeyboardControls();

        var bulletsContainer = new BulletsContainer();
        var playersContainer = new PlayersContainer<>();

        var bulletsRenderer = new ContainerRenderer<>(bulletsContainer, VisibleRenderer::new);
        var playersRenderer = new ContainerRenderer<>(playersContainer, PlayerRenderer::new);

        var env = System.getenv();
        var protocol = env.getOrDefault("PROTOCOL", "http");
        var host = env.getOrDefault("HOST", "localhost");
        var port = Integer.parseInt(env.getOrDefault("PORT", "8080"));
        var localStateSynchronizer = new LocalStateSynchronizer();
        var client = new SocketIoClient(protocol, host, port, new ReentrantLock(), localStateSynchronizer, new Delay(100));

        asteroids = AsteroidsClientScreen.builder()
                .bulletsContainer(bulletsContainer)
                .playersContainer(playersContainer)
                .client(client)
                .localStateSynchronizer(localStateSynchronizer)
                .bulletsRenderer(bulletsRenderer)
                .playersRenderer(playersRenderer)
                .shapeRenderer(shapeRenderer)
                .localControls(localControls)
                .viewport(viewport)
                .arena(new Arena(WORLD_WIDTH, WORLD_HEIGHT))
                .build();

        setScreen(asteroids);
    }

    @Override
    public void dispose() {
        asteroids.dispose();
    }
}
