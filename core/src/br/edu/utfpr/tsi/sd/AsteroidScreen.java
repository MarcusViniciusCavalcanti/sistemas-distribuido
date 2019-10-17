package br.edu.utfpr.tsi.sd;

import br.edu.utfpr.tsi.sd.container.Container;
import br.edu.utfpr.tsi.sd.model.Arena;
import br.edu.utfpr.tsi.sd.model.Bullet;
import br.edu.utfpr.tsi.sd.model.Player;
import br.edu.utfpr.tsi.sd.model.Ship;
import br.edu.utfpr.tsi.sd.view.ContainerRenderer;
import br.edu.utfpr.tsi.sd.view.Renderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

public class AsteroidScreen extends ScreenAdapter {
    private final Viewport viewport;
    private final ShapeRenderer shapeRenderer;
    private final Arena arena;
    private final Player player;
    private final Container<Bullet> bulletsContainer;
    private final Renderer playerRenderer;
    private final ContainerRenderer<Bullet> bulletsRenderer;

    public AsteroidScreen(
            Viewport viewport, ShapeRenderer shapeRenderer,
            Arena arena, Player player, Container<Bullet> bulletsContainer,
            Renderer playerRenderer, ContainerRenderer<Bullet> bulletsRenderer) {

        this.viewport = viewport;
        this.shapeRenderer = shapeRenderer;
        this.arena = arena;
        this.player = player;
        this.bulletsContainer = bulletsContainer;
        this.playerRenderer = playerRenderer;
        this.bulletsRenderer = bulletsRenderer;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update(delta);
        player.getShip()
                .ifPresent(arena::ensurePlacementWithinBounds);
        player.getShip()
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
