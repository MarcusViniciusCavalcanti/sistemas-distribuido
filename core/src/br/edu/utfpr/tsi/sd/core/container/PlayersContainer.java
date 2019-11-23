package br.edu.utfpr.tsi.sd.core.container;


import br.edu.utfpr.tsi.sd.core.model.Bullet;
import br.edu.utfpr.tsi.sd.core.model.Player;
import br.edu.utfpr.tsi.sd.core.model.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public class PlayersContainer<P extends Player> implements Container<P> {
    private final List<P> players;

    public PlayersContainer(List<P> players) {
        this.players = players;
    }

    public PlayersContainer() {
        this(new ArrayList<>());
    }

    @Override
    public void add(P toAdd) {
        players.add(toAdd);
    }

    @Override
    public List<P> getAll() {
        return players;
    }

    @Override
    public void update() {
        players.forEach(Player::update);
    }

    @Override
    public void move(float delta) {
        players.forEach(player -> player.move(delta));
    }

    public Stream<Ship> streamShips() {
        return stream()
                .map(Player::getShip)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    public Stream<Bullet> obtainAndStreamBullets() {
        return streamShips()
                .map(Ship::obtainBullet)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }
}
