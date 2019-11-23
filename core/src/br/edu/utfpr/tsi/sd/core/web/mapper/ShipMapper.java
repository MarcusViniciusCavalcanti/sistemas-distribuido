package br.edu.utfpr.tsi.sd.core.web.mapper;

import br.edu.utfpr.tsi.sd.core.web.impl.ShipDto;
import br.edu.utfpr.tsi.sd.core.model.Player;
import br.edu.utfpr.tsi.sd.core.model.Ship;
import com.badlogic.gdx.math.Vector2;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShipMapper {

    public static ShipDto fromShip(Ship ship) {
        var shipPosition = ship.getPosition();
        var velocity = ship.getVelocity();
        return ShipDto.builder()
                .rotation(ship.getRotation())
                .rotationVelocity(ship.getRotationVelocity())
                .velocityX(velocity.x)
                .velocityY(velocity.y)
                .x(shipPosition.x)
                .y(shipPosition.y)
                .build();
    }

    // TODO verificar melhor forma para remover o if null
    public static Ship fromDto(ShipDto dto, Player owner) {
        if(dto == null) return null;

        var startingPosition = new Vector2(dto.getX(), dto.getY());
        return new Ship(owner, startingPosition, dto.getRotation());
    }

    public static void updateByDto(Ship ship, ShipDto dto) {
        ship.setPosition(new Vector2(dto.getX(), dto.getY()));
        ship.setRotation(dto.getRotation());
        ship.setVelocity(new Vector2(dto.getVelocityX(), dto.getVelocityY()));
        ship.setRotationVelocity(dto.getRotationVelocity());
    }
}
