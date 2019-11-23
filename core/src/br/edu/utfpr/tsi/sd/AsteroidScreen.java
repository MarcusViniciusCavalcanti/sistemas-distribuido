package br.edu.utfpr.tsi.sd;

import br.edu.utfpr.tsi.sd.core.container.Container;
import br.edu.utfpr.tsi.sd.core.manager.Collider;
import br.edu.utfpr.tsi.sd.core.manager.Respawner;
import br.edu.utfpr.tsi.sd.core.model.Arena;
import br.edu.utfpr.tsi.sd.core.model.Bullet;
import br.edu.utfpr.tsi.sd.core.model.Player;
import br.edu.utfpr.tsi.sd.core.model.Ship;
import br.edu.utfpr.tsi.sd.core.view.ContainerRenderer;
import br.edu.utfpr.tsi.sd.core.view.Renderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class AsteroidScreen extends ScreenAdapter {
    private final Viewport viewport;
    private final ShapeRenderer shapeRenderer;
    private final Arena arena;
    private final Player player;
    private final Container<Bullet> bulletsContainer;
    private final Renderer playerRenderer;
    private final ContainerRenderer<Bullet> bulletsRenderer;
    private final Respawner respawner;
    private final Collider collider;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update(delta);
        player.ship()
                .ifPresent(arena::ensurePlacementWithinBounds);
        player.ship()
                .flatMap(Ship::obtainBullet)
                .ifPresent(bulletsContainer::add);


        bulletsContainer.update(delta);
        bulletsContainer.stream()
                .forEach(arena::ensurePlacementWithinBounds);

        viewport.apply();
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        playerRenderer.render(shapeRenderer);
        bulletsRenderer.render(shapeRenderer);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
