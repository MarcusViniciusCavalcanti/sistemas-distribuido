package br.edu.utfpr.tsi.sd.model;

import br.edu.utfpr.tsi.sd.controller.Controls;
import com.badlogic.gdx.graphics.Color;

import java.util.Optional;

public class Player {
    private final Controls controls;

    private final Color color;

    private Ship ship;

    public Player(Controls controls, Color color) {
        this.controls = controls;
        this.color = color;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void update(float delta) {
        getShip().ifPresent(ship -> {
            ship.control(controls, delta);
            ship.update(delta);
        });
    }

    public Optional<Ship> getShip() {
        return Optional.ofNullable(this.ship);
    }

    public Color getColor() {
        return color;
    }
}
