package br.edu.utfpr.tsi.sd.model;

import br.edu.utfpr.tsi.sd.tools.Vectors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Bullet implements Visible {

    private static final float[] VERTICES = new float[] {
            0, 0,
            2, 0,
            2, 2,
            0, 2
    };
    private static final float SPEED = 500f;
    private static final float RANGE = 400f;

    private final Player shooter;
    private final Polygon shape;
    private float remainingRange;

    public Bullet(Player shooter, Vector2 startPosition, float rotation) {
        shape = new Polygon(VERTICES);
        shape.setPosition(startPosition.x, startPosition.y);
        shape.setRotation(rotation);
        shape.setOrigin(0, -Ship.getMiddle().y);

        this.shooter = shooter;

        remainingRange = RANGE;
    }

    @Override
    public Color getColor() {
        return shooter.getColor();
    }

    @Override
    public Polygon getShape() {
        return shape;
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
}
