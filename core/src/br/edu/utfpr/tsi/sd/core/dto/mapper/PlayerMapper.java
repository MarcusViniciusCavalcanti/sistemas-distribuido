package br.edu.utfpr.tsi.sd.core.dto.mapper;

import br.edu.utfpr.tsi.sd.core.controller.RemoteControl;
import br.edu.utfpr.tsi.sd.core.dto.PlayerDTO;
import br.edu.utfpr.tsi.sd.core.model.Player;
import br.edu.utfpr.tsi.sd.core.model.RemotePlayer;
import com.badlogic.gdx.graphics.Color;

import java.util.UUID;

public class PlayerMapper {
    public static PlayerDTO fromPlayer(Player player) {
        return new PlayerDTO(player.getId().toString(), player.getColor().toString(),
                player.ship()
                        .map(ShipMapper::fromShip)
                        .orElseGet(() -> null)
        );
    }

    public static RemotePlayer remotePlayerFromDto(PlayerDTO dto) {
        return new RemotePlayer(new RemoteControl(),
                Color.valueOf(dto.getColor()), UUID.fromString(dto.getId()));
    }
}
