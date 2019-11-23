package br.edu.utfpr.tsi.sd.core.model;

import br.edu.utfpr.tsi.sd.core.tools.Vectors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;

import java.util.UUID;

public class Bullet implements Visible, Identifiable {

    private boolean hasHitSomething;

    @Getter
    private final UUID id;

    private final Player shooter;

    @Getter
    private final Polygon shape;

    private float remainingRange;

    private static final float[] VERTICES = new float[] {
            0, 0,
            2, 0,
            2, 2,
            0, 2
    };
    private static final float SPEED = 500f;
    private static final float RANGE = 400f;

    public Bullet(UUID id, Player shooter, Vector2 startPosition, float rotation) {
        this.id = id;
        this.shooter = shooter;
        this.remainingRange = RANGE;
        this.hasHitSomething = false;

        shape = new Polygon(VERTICES);
        shape.setPosition(startPosition.x, startPosition.y);
        shape.setRotation(rotation);
        shape.setOrigin(0, -Ship.getMiddle().y);
    }

    @Override
    public Color getColor() {
        return shooter.getColor();
    }

    public UUID getShooterId() {
        return shooter.getId();
    }

    public void move(float delta) {
        var direction = Vectors.getDirectionVector(shape.getRotation());
        var movement = new Vector2(direction.x * delta * SPEED, direction.y * delta * SPEED);

        remainingRange -= movement.len();
        shape.translate(movement.x, movement.y);
    }

    public boolean isInRange() {
        return remainingRange > 0;
    }

    public void noticeHit() {
        hasHitSomething = true;
    }

    public boolean hasHitSomething() {
        return hasHitSomething;
    }
}
