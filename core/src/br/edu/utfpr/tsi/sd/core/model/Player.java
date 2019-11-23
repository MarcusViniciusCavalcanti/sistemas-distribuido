package br.edu.utfpr.tsi.sd.core.model;

import br.edu.utfpr.tsi.sd.core.controller.Controls;
import br.edu.utfpr.tsi.sd.core.controller.RemoteControls;
import com.badlogic.gdx.graphics.Color;
import lombok.*;

import java.util.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Player implements Identifiable {
    public static final List<Color> POSSIBLE_COLORS = List.of(
            Color.WHITE,
            Color.GRAY,
            Color.BLUE,
            Color.GREEN,
            Color.ORANGE,
            Color.LIGHT_GRAY,
            Color.MAGENTA,
            Color.CHARTREUSE,
            Color.LIME,
            Color.OLIVE,
            Color.CORAL,
            Color.CYAN,
            Color.FIREBRICK
        );

    @Getter
    private final UUID id;

    @Getter
    private final Color color;

    @Setter
    @Getter
    private Controls controls;

    @Setter
    private Ship ship;

    public Player(UUID id, RemoteControls remoteControls, Color color) {
        this.color = color;
        this.controls = remoteControls;
        this.id = id;
    }

    public void noticeHit() {
        this.ship = null;
    }

    public void update() {
        getShip().ifPresent(Ship::update);
    }

    public void move(float delta) {
        getShip().ifPresent(ship -> {
            ship.control(controls, delta);
            ship.move(delta);
        });
    }

    public Optional<Ship> getShip() {
        return Optional.ofNullable(ship);
    }
}
