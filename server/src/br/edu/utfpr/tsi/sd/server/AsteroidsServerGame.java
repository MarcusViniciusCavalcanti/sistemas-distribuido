package br.edu.utfpr.tsi.sd.server;

import br.edu.utfpr.tsi.sd.core.container.BulletsContainer;
import br.edu.utfpr.tsi.sd.core.container.PlayersContainer;
import br.edu.utfpr.tsi.sd.core.providers.Collider;
import br.edu.utfpr.tsi.sd.core.providers.Respawner;
import br.edu.utfpr.tsi.sd.core.model.Arena;
import br.edu.utfpr.tsi.sd.core.model.RemotePlayer;
import br.edu.utfpr.tsi.sd.server.connection.Server;
import br.edu.utfpr.tsi.sd.server.connection.SocketIoServer;
import br.edu.utfpr.tsi.sd.server.connection.synchronization.StateIndexByClient;
import br.edu.utfpr.tsi.sd.core.tools.Delay;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import java.util.Map;

import static br.edu.utfpr.tsi.sd.AsteroidsGame.WORLD_HEIGHT;
import static br.edu.utfpr.tsi.sd.AsteroidsGame.WORLD_WIDTH;

public class AsteroidsServerGame extends Game {
    private Screen asteroids;

    @Override
    public void create() {
        var arena = new Arena(WORLD_WIDTH, WORLD_HEIGHT);
        var bulletsContainer = new BulletsContainer();
        var playersContainer = new PlayersContainer<RemotePlayer>();
        var respawner = new Respawner<>(playersContainer, WORLD_WIDTH, WORLD_HEIGHT);
        var collider = new Collider<>(playersContainer, bulletsContainer);

        var env = System.getenv();
        var host = env.getOrDefault("HOST", "localhost");
        var port = Integer.parseInt(env.getOrDefault("PORT", "8080"));
        var server = new SocketIoServer(host, port, new StateIndexByClient(), new Delay(100));

        asteroids = AsteroidsServerScreen.builder()
                .server(server)
                .arena(arena)
                .bulletsContainer(bulletsContainer)
                .playersContainer(playersContainer)
                .respawner(respawner)
                .collider(collider)
                .build();

        setScreen(asteroids);
    }

    @Override
    public void dispose() {
        asteroids.dispose();
    }
}
