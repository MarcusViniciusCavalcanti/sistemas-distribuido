package br.edu.utfpr.tsi.sd.core.web.mapper;

import br.edu.utfpr.tsi.sd.core.container.Container;
import br.edu.utfpr.tsi.sd.core.errors.EntityNotFoundException;
import br.edu.utfpr.tsi.sd.core.web.impl.BulletDto;
import br.edu.utfpr.tsi.sd.core.model.Bullet;
import br.edu.utfpr.tsi.sd.core.model.Player;
import com.badlogic.gdx.math.Vector2;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BulletMapper {

    public static BulletDto fromBullet(Bullet bullet) {
        var position = bullet.getPosition();

        return BulletDto.builder()
                .id(bullet.getId().toString())
                .rotation(bullet.getRotation())
                .shooterId(bullet.getShooterId().toString())
                .x(position.x)
                .y(position.y)
                .build();
    }

    public static Bullet fromDto(BulletDto dto, Container<Player> playersContainer) {
        var shooter = playersContainer.getById(dto.getShooterId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot find Player of id " + dto.getShooterId() + " to create a Bullet."));
        var startPosition = new Vector2(dto.getX(), dto.getY());

        return new Bullet(UUID.fromString(dto.getId()), shooter, startPosition, dto.getRotation());
    }

    public static void updateByDto(Bullet bullet, BulletDto dto) {
        bullet.setPosition(new Vector2(dto.getX(), dto.getY()));
    }
}
