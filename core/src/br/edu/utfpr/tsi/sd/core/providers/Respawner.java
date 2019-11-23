package br.edu.utfpr.tsi.sd.core.providers;


import br.edu.utfpr.tsi.sd.core.container.Container;
import br.edu.utfpr.tsi.sd.core.model.Player;
import br.edu.utfpr.tsi.sd.core.model.Ship;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Respawner<P extends Player> {
    private static final Random random = new Random();
    private final Container<P> playersContainer;
    private final float widthBound;
    private final float heightBound;

    public Respawner(Container<P> playersContainer, float widthBound, float heightBound) {
        this.playersContainer = playersContainer;
        this.widthBound = widthBound;
        this.heightBound = heightBound;
    }

    // TODO
    public void respawn() {
        playersContainer.stream()
                .filter(player -> !player.getShip().isPresent())
                .forEach(this::respawnFor);
    }

    public void respawnFor(Player player) {
        player.setShip(new Ship(player, randomRespawnPoint(), 0));
    }

    private Vector2 randomRespawnPoint() {
        return new Vector2(random.nextInt(Math.round(widthBound)), random.nextInt(Math.round(heightBound)));
    }
}
