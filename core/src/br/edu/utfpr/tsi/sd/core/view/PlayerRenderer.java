package br.edu.utfpr.tsi.sd.core.view;

import br.edu.utfpr.tsi.sd.core.model.Player;
import br.edu.utfpr.tsi.sd.core.model.Ship;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Map;
import java.util.WeakHashMap;

public class PlayerRenderer implements Renderer {

    private final Map<Ship, Renderer> cache;

    private final Player player;

    public PlayerRenderer(Player player, Map<Ship, Renderer> cache) {
        this.player = player;
        this.cache = cache;
    }

    public PlayerRenderer(Player player) {
        this(player, new WeakHashMap<>());
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        player.ship().ifPresent(ship -> cache.computeIfAbsent(ship, VisibleRenderer::new).render(shapeRenderer));
    }
}
