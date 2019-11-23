package br.edu.utfpr.tsi.sd.core.manager;

import br.edu.utfpr.tsi.sd.core.container.Container;
import br.edu.utfpr.tsi.sd.core.model.Bullet;
import br.edu.utfpr.tsi.sd.core.model.Player;

public class Collider<P extends Player> {
    private final Container<P> playersContainer;
    private final Container<Bullet> bulletsContainer;

    public Collider(Container<P> playersContainer, Container<Bullet> bulletsContainer) {
        this.playersContainer = playersContainer;
        this.bulletsContainer = bulletsContainer;
    }

    public void checkCollisions() {
        bulletsContainer.stream()
                .forEach(bullet -> playersContainer.stream()
                        .filter(player -> player.ship().isPresent())
                        .filter(player -> player.ship().get().collidesWith(bullet))
                        .findFirst()
                        .ifPresent(player -> {
                            player.noticeHit();
                            bullet.noticeHit();
                        }));
    }
}
