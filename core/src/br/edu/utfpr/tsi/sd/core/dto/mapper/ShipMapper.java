package br.edu.utfpr.tsi.sd.core.dto.mapper;

import br.edu.utfpr.tsi.sd.core.dto.ShipDTO;
import br.edu.utfpr.tsi.sd.core.model.Ship;
import com.badlogic.gdx.math.Vector2;

public class ShipMapper {
    public static ShipDTO fromShip(Ship ship) {
        Vector2 shipPosition = ship.getPosition();
        return new ShipDTO(shipPosition.x, shipPosition.y, ship.getRotation());
    }
}
