package br.edu.utfpr.tsi.sd.core.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public interface Visible extends Serializable {

    Color getColor();

    Polygon getShape();

    default Vector2 getPosition() {
        return new Vector2(getShape().getX(), getShape().getY());
    }

    default void setPosition(Vector2 position) {
        getShape().setPosition(position.x, position.y);
    }

    default float getRotation() {
        return getShape().getRotation();
    }

    default void setRotation(float degrees) {
        getShape().setRotation(degrees);
    }

    default boolean collidesWith(Visible anotherVisible) {
        return Intersector.overlapConvexPolygons(getShape(), anotherVisible.getShape());
    }
}
