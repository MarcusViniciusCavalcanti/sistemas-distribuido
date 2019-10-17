package br.edu.utfpr.tsi.sd;

import br.edu.utfpr.tsi.sd.container.Container;
import br.edu.utfpr.tsi.sd.model.Arena;
import br.edu.utfpr.tsi.sd.model.Bullet;
import br.edu.utfpr.tsi.sd.model.Player;
import br.edu.utfpr.tsi.sd.view.ContainerRenderer;
import br.edu.utfpr.tsi.sd.view.Renderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

public final class AsteroidScreenBuilder {
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;
    private Arena arena;
    private Player player;
    private Container<Bullet> bulletsContainer;
    private Renderer playerRenderer;
    private ContainerRenderer<Bullet> bulletsRenderer;

    private AsteroidScreenBuilder() {
    }

    public static AsteroidScreenBuilder anAsteroidScreen() {
        return new AsteroidScreenBuilder();
    }

    public AsteroidScreenBuilder withViewport(Viewport viewport) {
        this.viewport = viewport;
        return this;
    }

    public AsteroidScreenBuilder withShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
        return this;
    }

    public AsteroidScreenBuilder withArena(Arena arena) {
        this.arena = arena;
        return this;
    }

    public AsteroidScreenBuilder withPlayer(Player player) {
        this.player = player;
        return this;
    }

    public AsteroidScreenBuilder withBulletsContainer(Container<Bullet> bulletsContainer) {
        this.bulletsContainer = bulletsContainer;
        return this;
    }

    public AsteroidScreenBuilder withPlayerRenderer(Renderer playerRenderer) {
        this.playerRenderer = playerRenderer;
        return this;
    }

    public AsteroidScreenBuilder withBulletsRenderer(ContainerRenderer<Bullet> bulletsRenderer) {
        this.bulletsRenderer = bulletsRenderer;
        return this;
    }

    public AsteroidScreen build() {
        return new AsteroidScreen(viewport, shapeRenderer, arena, player, bulletsContainer, playerRenderer, bulletsRenderer);
    }
}
