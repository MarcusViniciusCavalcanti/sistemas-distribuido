package br.edu.utfpr.tsi.sd.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class PlayerDTO implements Dto {

    @JsonProperty("id") private final String id;
    @JsonProperty("color") private final String color;
    @JsonProperty("ship") private final ShipDTO shipDto;

}
