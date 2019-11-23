package br.edu.utfpr.tsi.sd.server;

import br.edu.utfpr.tsi.sd.AsteroidsGame;
import br.edu.utfpr.tsi.sd.core.container.BulletsContainer;
import br.edu.utfpr.tsi.sd.core.container.PlayersContainer;
import br.edu.utfpr.tsi.sd.core.manager.Collider;
import br.edu.utfpr.tsi.sd.core.manager.Respawner;
import br.edu.utfpr.tsi.sd.core.model.Arena;
import br.edu.utfpr.tsi.sd.core.model.Player;
import br.edu.utfpr.tsi.sd.core.model.RemotePlayer;
import br.edu.utfpr.tsi.sd.server.connection.SocketIoServer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import static br.edu.utfpr.tsi.sd.AsteroidsGame.WORLD_HEIGHT;
import static br.edu.utfpr.tsi.sd.AsteroidsGame.WORLD_WIDTH;

public class AsteroidsServerGame extends Game {
    private Screen asteroids;

    @Override
    public void create() {
        var playersContainer = new PlayersContainer<RemotePlayer>();
        var arena = new Arena(WORLD_WIDTH, WORLD_HEIGHT);
        var bulletsContainer = new BulletsContainer();

        var respawner = Respawner.<RemotePlayer>builder()
                .widthBound(WORLD_WIDTH)
                .heightBound(WORLD_HEIGHT)
                .playersContainer(playersContainer)
                .build();

        var collider = new Collider<>(playersContainer, bulletsContainer);

        var env = System.getenv();
        var host = env.getOrDefault("HOST", "localhost");
        var port = Integer.parseInt(env.getOrDefault("PORT", "8080"));
        var server = new SocketIoServer(host, port);

        asteroids = AsteroidsServerScreen.builder()
                .server(server)
                .arena(arena)
                .bulletsContainer(bulletsContainer)
                .playersContainer(playersContainer)
                .collider(collider)
                .respawner(respawner)
                .build();

        setScreen(asteroids);
    }

    @Override
    public void dispose() {
        asteroids.dispose();
    }
}
