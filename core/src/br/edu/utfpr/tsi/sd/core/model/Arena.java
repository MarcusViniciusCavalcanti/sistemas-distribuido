package br.edu.utfpr.tsi.sd.core.model;

import com.badlogic.gdx.math.Rectangle;

public class Arena {
    private final Rectangle bounds;

    public Arena(float width, float height) {
        bounds = new Rectangle(0, 0, width, height);
    }

    public void ensurePlacementWithinBounds(Visible visible) {
        var shape = visible.getShape();
        var shapeBounds = shape.getBoundingRectangle();
        float x = shape.getX();
        float y = shape.getY();

        // todo melhorar
        if(x + shapeBounds.width < bounds.x) x = bounds.width;
        if(x > bounds.width) x = bounds.x - shapeBounds.width;

        if(y + shapeBounds.height < bounds.y) y = bounds.height;
        if(y > bounds.height) y = bounds.y - shapeBounds.height;

        shape.setPosition(x, y);
    }
}
