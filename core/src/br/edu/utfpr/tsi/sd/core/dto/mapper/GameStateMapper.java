package br.edu.utfpr.tsi.sd.core.dto.mapper;

import br.edu.utfpr.tsi.sd.core.container.Container;
import br.edu.utfpr.tsi.sd.core.dto.BulletDTO;
import br.edu.utfpr.tsi.sd.core.dto.GameStateDTO;
import br.edu.utfpr.tsi.sd.core.dto.PlayerDTO;
import br.edu.utfpr.tsi.sd.core.model.Bullet;
import br.edu.utfpr.tsi.sd.core.model.Player;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GameStateMapper {
    public static GameStateDTO fromState(Container<? extends Player> players, Container<Bullet> bullets) {
        List<PlayerDTO> playerDtos = players.stream()
                .map(PlayerMapper::fromPlayer)
                .collect(toList());
        List<BulletDTO> bulletDtos = bullets.stream()
                .map(BulletMapper::fromBullet)
                .collect(toList());

        return new GameStateDTO(playerDtos, bulletDtos);
    }
}
