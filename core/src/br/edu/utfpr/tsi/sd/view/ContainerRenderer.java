package br.edu.utfpr.tsi.sd.view;

import br.edu.utfpr.tsi.sd.container.Container;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Function;

public class ContainerRenderer<T> implements Renderer {

    private final Container<T> container;

    private final Function<T, Renderer> rendererFactory;

    private final Map<T, Renderer> cache;

    public ContainerRenderer(Container<T> container, Function<T, Renderer> rendererFactory, Map<T, Renderer> cache) {
        this.container = container;
        this.rendererFactory = rendererFactory;
        this.cache = cache;
    }

    public ContainerRenderer(Container<T> container, Function<T, Renderer> rendererFactory) {
        this(container, rendererFactory, new WeakHashMap<>());
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        container.stream().forEach(thing -> cache.computeIfAbsent(thing, rendererFactory).render(shapeRenderer));
    }
}
