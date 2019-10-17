package br.edu.utfpr.tsi.sd.container;

import br.edu.utfpr.tsi.sd.model.Bullet;

import java.util.ArrayList;
import java.util.List;

public class BulletsContainer implements Container<Bullet> {

    private final List<Bullet> bullets;

    public BulletsContainer(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public BulletsContainer() {
        this(new ArrayList<>());
    }

    @Override
    public void add(Bullet bullet) {
        bullets.add(bullet);
    }

    @Override
    public List<Bullet> getAll() {
        return bullets;
    }

    @Override
    public void update(float delta) {
        bullets.forEach(bullet -> bullet.move(delta));
        bullets.removeIf(bullet -> !bullet.isInRange());
    }
}
