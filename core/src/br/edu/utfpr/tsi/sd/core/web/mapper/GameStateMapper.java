package br.edu.utfpr.tsi.sd.core.web.mapper;

import br.edu.utfpr.tsi.sd.core.container.Container;
import br.edu.utfpr.tsi.sd.core.model.Bullet;
import br.edu.utfpr.tsi.sd.core.model.Player;
import br.edu.utfpr.tsi.sd.core.web.impl.GameStateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameStateMapper {

    public static GameStateDto fromState(Container<? extends Player> players, Container<Bullet> bullets) {
        var playerDtos = players.stream()
                .map(PlayerMapper::fromPlayer)
                .collect(toList());
        var bulletDtos = bullets.stream()
                .map(BulletMapper::fromBullet)
                .collect(toList());

        return GameStateDto.builder()
                .players(playerDtos)
                .bullets(bulletDtos)
                .build();
    }
}
