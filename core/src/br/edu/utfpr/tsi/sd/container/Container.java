package br.edu.utfpr.tsi.sd.container;

import java.util.List;
import java.util.stream.Stream;

public interface Container<T> {

    void add(T toAdd);

    List<T> getAll();

    void update(float delta);

    default Stream<T> stream() {
        return getAll().stream();
    }

}
