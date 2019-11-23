package br.edu.utfpr.tsi.sd.core.container;

import br.edu.utfpr.tsi.sd.core.model.Identifiable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface Container<T extends Identifiable> {
    List<T> getAll();

    void add(T toAdd);

    void update();

    void move(float delta);

    default Stream<T> stream() {
        return getAll().stream();
    }

    default Optional<T> getById(UUID id) {
        return stream()
                .filter(identifiable -> identifiable.isIdEqual(id))
                .findAny();
    }

    default Optional<T> getById(String id) {
        return getById(UUID.fromString(id));
    }

    default void removeById(UUID id) {
        getAll().removeIf(identifiable -> identifiable.isIdEqual(id));
    }

    default void removeById(String id) {
        removeById(UUID.fromString(id));
    }
}
