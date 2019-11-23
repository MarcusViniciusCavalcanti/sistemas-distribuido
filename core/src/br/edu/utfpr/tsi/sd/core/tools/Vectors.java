package br.edu.utfpr.tsi.sd.core.tools;

import com.badlogic.gdx.math.Vector2;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Vectors {

    public static Vector2 getDirectionVector(float rotation) {
        return new Vector2(-(float) Math.sin(Math.toRadians(rotation)), (float) Math.cos(Math.toRadians(rotation)));
    }
}
