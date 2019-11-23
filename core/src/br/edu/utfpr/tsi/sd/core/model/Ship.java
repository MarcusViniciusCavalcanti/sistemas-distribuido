package br.edu.utfpr.tsi.sd.core.model;

import br.edu.utfpr.tsi.sd.core.controller.Controls;
import br.edu.utfpr.tsi.sd.core.tools.Vectors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class Ship implements Visible {
    private static final float[] VERTICES = new float[] {
            0, 0,
            16, 32,
            32, 0,
            16, 10
    };
    private static final float MAX_SPEED = 2000f;
    private static final float ACCELERATION = 500f;
    private static final float ROTATION = 10f;
    private static final float DRAG = 2f;

    private static final Vector2 MIDDLE = new Vector2(16, 16);
    private static final Vector2 BULLET_OUTPUT = new Vector2(16, 32);
    private static final Duration SHOT_INTERVAL = Duration.ofMillis(300);

    private final Player owner;

    @Getter
    private final Polygon shape;

    @Getter
    private final Vector2 velocity;

    @Getter
    @Setter
    private float rotationVelocity;

    private Instant lastShot;
    private boolean canShoot;
    private boolean wantsToShoot;

    public Ship(Player owner, Vector2 startingPosition, float startingRotation) {
        shape = new Polygon(VERTICES);
        shape.setOrigin(MIDDLE.x, MIDDLE.y);
        shape.setPosition(startingPosition.x, startingPosition.y);
        shape.setRotation(startingRotation);

        this.owner = owner;
        velocity = new Vector2(0, 0);
        lastShot = Instant.EPOCH;
    }

    public static Vector2 getMiddle() {
        return new Vector2(MIDDLE);
    }

    public void control(Controls controls, float delta) {
        if(controls.forward()) moveForwards(delta);
        if(controls.left()) rotateLeft(delta);
        if(controls.right()) rotateRight(delta);
        wantsToShoot = controls.shoot();
    }

    public void update() {
        applyShootingPossibility();
    }

    public void move(float delta) {
        applyMovement(delta);
    }

    public Optional<Bullet> obtainBullet() {
        if(canShoot && wantsToShoot) {
            lastShot = Instant.now();
            return Optional.of(new Bullet(
                            UUID.randomUUID(),
                            owner,
                            bulletStartingPosition(),
                            shape.getRotation())
            );
        }
        return Optional.empty();
    }

    @Override
    public Color getColor() {
        return owner.getColor();
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity.set(velocity);
    }

    private Vector2 getDirection() {
        return Vectors.getDirectionVector(shape.getRotation());
    }

    private void moveForwards(float delta) {
        var direction = getDirection();
        velocity.x += delta * ACCELERATION * direction.x;
        velocity.y += delta * ACCELERATION * direction.y;
    }

    private void rotateLeft(float delta) {
        rotationVelocity += delta * ROTATION;
    }

    private void rotateRight(float delta) {
        rotationVelocity -= delta * ROTATION;
    }

    private void applyMovement(float delta) {
        velocity.clamp(0, MAX_SPEED);

        velocity.x -= delta * DRAG * velocity.x;
        velocity.y -= delta * DRAG * velocity.y;
        rotationVelocity -= delta * DRAG * rotationVelocity;

        var x = delta * velocity.x;
        var y = delta * velocity.y;

        shape.translate(x, y);
        shape.rotate(rotationVelocity);
    }

    private void applyShootingPossibility() {
        canShoot = Instant.now().isAfter(lastShot.plus(SHOT_INTERVAL));
    }

    private Vector2 bulletStartingPosition() {
        return new Vector2(shape.getX(), shape.getY()).add(BULLET_OUTPUT);
    }
}
