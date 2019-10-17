package br.edu.utfpr.tsi.sd.model;

import com.badlogic.gdx.math.Rectangle;

public class Arena {

    private final Rectangle bounds;


    public Arena(float width, float height) {
        this.bounds = new Rectangle(0, 0, width, height);
    }

    public void ensurePlacementWithinBounds(Visible visible) {
        var shape = visible.getShape();
        var shapeBounds = shape.getBoundingRectangle();

        var x = shape.getX();
        var y = shape.getY();

        if (x + shapeBounds.width < bounds.x) {
            x = bounds.width;
        }

        if (y + shapeBounds.height < bounds.y) {
            y = bounds.height;
        }

        if (x > bounds.width) {
            x = bounds.x - shapeBounds.width;
        }

        if (y > bounds.height) {
            y = bounds.y - shapeBounds.height;
        }

        shape.setPosition(x, y);
    }
}
