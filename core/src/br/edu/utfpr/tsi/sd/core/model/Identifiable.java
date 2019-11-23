package br.edu.utfpr.tsi.sd.core.model;

import java.util.UUID;

public interface Identifiable {

    UUID getId();

    default boolean isIdEqual(UUID otherId) {
        return getId().equals(otherId);
    }
}
