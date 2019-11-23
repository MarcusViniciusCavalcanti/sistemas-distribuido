package br.edu.utfpr.tsi.sd.core.model;

import br.edu.utfpr.tsi.sd.core.controller.Controls;
import com.badlogic.gdx.graphics.Color;
import lombok.*;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Player implements Serializable, Identifiable {

    private final Controls controls;

    @Setter
    private Ship ship;

    @Getter
    private final Color color;

    @Getter
    private final UUID id;

    public Player(Controls controls, Color color, UUID id) {
        this.controls = controls;
        this.color = color;
        this.id = id;
    }

    public void update(float delta) {
        ship().ifPresent(ship -> {
            ship.control(controls, delta);
            ship.update(delta);
        });
    }

    public Optional<Ship> ship() {
        return Optional.ofNullable(this.ship);
    }


    public void noticeHit() {
        this.ship = null;
    }

}
