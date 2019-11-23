package br.edu.utfpr.tsi.sd.core.web.impl;

import br.edu.utfpr.tsi.sd.core.web.dto.Dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class PlayerDto implements Dto {
    private final String id;
    private final String color;
    private final ShipDto shipDto;

    @JsonCreator
    public PlayerDto(
            @JsonProperty("id") String id,
            @JsonProperty("color") String color,
            @JsonProperty("ship") ShipDto shipDto) {
        this.id = id;
        this.color = color;
        this.shipDto = shipDto;
    }
}
