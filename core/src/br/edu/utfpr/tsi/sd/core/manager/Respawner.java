package br.edu.utfpr.tsi.sd.core.manager;

import br.edu.utfpr.tsi.sd.core.container.Container;
import br.edu.utfpr.tsi.sd.core.model.Player;
import br.edu.utfpr.tsi.sd.core.model.Ship;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Respawner<P extends Player> {
    private final Container<P> playersContainer;
    private final float widthBound;
    private final float heightBound;

    private static final Random random = new Random();

    Respawner(Container<P> playersContainer, float widthBound, float heightBound) {
        this.playersContainer = playersContainer;
        this.widthBound = widthBound;
        this.heightBound = heightBound;
    }

    public static <P extends Player> RespawnerBuilder<P> builder() {
        return new RespawnerBuilder<>();
    }

    public void respawn() {
        playersContainer.stream()
                .filter(player -> !player.ship().isPresent())
                .forEach(this::respawnFor);
    }

    public void respawnFor(Player player) {
        var ship = Ship.builder()
                .owner(player)
                .rotation(0)
                .startPosition(randomRespawnPoint())
                .build();

        player.setShip(ship);
    }

    private Vector2 randomRespawnPoint() {
        return new Vector2(random.nextInt(Math.round(widthBound)), random.nextInt(Math.round(heightBound)));
    }

    public static class RespawnerBuilder<P extends Player> {
        private Container<P> playersContainer;
        private float widthBound;
        private float heightBound;

        RespawnerBuilder() {
        }

        public RespawnerBuilder<P> playersContainer(Container<P> playersContainer) {
            this.playersContainer = playersContainer;
            return this;
        }

        public RespawnerBuilder<P> widthBound(float widthBound) {
            this.widthBound = widthBound;
            return this;
        }

        public RespawnerBuilder<P> heightBound(float heightBound) {
            this.heightBound = heightBound;
            return this;
        }

        public Respawner<P> build() {
            return new Respawner<P>(playersContainer, widthBound, heightBound);
        }

        public String toString() {
            return "Respawner.RespawnerBuilder(playersContainer=" + this.playersContainer + ", widthBound=" + this.widthBound + ", heightBound=" + this.heightBound + ")";
        }
    }
}
