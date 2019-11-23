package br.edu.utfpr.tsi.sd.core.web.impl;

import br.edu.utfpr.tsi.sd.core.web.dto.Dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UuidDto implements Dto {
    private final String uuid;

    @JsonCreator
    public UuidDto(@JsonProperty("uuid") String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
