package br.edu.utfpr.tsi.sd.core.web.mapper;

import br.edu.utfpr.tsi.sd.core.controller.Controls;
import br.edu.utfpr.tsi.sd.core.controller.RemoteControls;
import br.edu.utfpr.tsi.sd.core.web.impl.PlayerDto;
import br.edu.utfpr.tsi.sd.core.web.impl.ShipDto;
import br.edu.utfpr.tsi.sd.core.model.Player;
import br.edu.utfpr.tsi.sd.core.model.RemotePlayer;
import br.edu.utfpr.tsi.sd.core.model.Ship;
import com.badlogic.gdx.graphics.Color;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.UUID;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerMapper {

    public static PlayerDto fromPlayer(Player player) {
        return new PlayerDto(player.getId().toString(), player.getColor().toString(),
                player.getShip()
                        .map(ShipMapper::fromShip)
                        .orElseGet(() -> null)
        );
    }

    public static RemotePlayer remotePlayerFromDto(PlayerDto dto) {
        return new RemotePlayer(UUID.fromString(dto.getId()), new RemoteControls(),
                    Color.valueOf(dto.getColor()));
    }

    public static Player localPlayerFromDto(PlayerDto dto, Controls controls) {
        var player = Player.builder()
                .controls(controls)
                .color(Color.valueOf(dto.getColor()))
                .id(UUID.fromString(dto.getId()))
                .build();

        player.setShip(ShipMapper.fromDto(dto.getShipDto(), player));
        return player;
    }

    public static void updateByDto(Player player, PlayerDto dto) {
        var currentShip = player.getShip();
        var shipDto = dto.getShipDto();

        // TODO refatorar
        if(currentShip.isPresent() && shipDto != null) {
            ShipMapper.updateByDto(currentShip.get(), shipDto);
        }
        else {
            player.setShip(ShipMapper.fromDto(shipDto, player));
        }
    }
}
