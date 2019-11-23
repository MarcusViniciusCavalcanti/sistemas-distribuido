package br.edu.utfpr.tsi.sd.core.dto.mapper;

import br.edu.utfpr.tsi.sd.core.dto.BulletDTO;
import br.edu.utfpr.tsi.sd.core.model.Bullet;
import com.badlogic.gdx.math.Vector2;

public class BulletMapper {
    public static BulletDTO fromBullet(Bullet bullet) {
        Vector2 position = bullet.getPosition();
        return new BulletDTO(bullet.getId().toString(),
                position.x, position.y, bullet.getRotation(),
                bullet.getShooterId().toString());
    }
}
