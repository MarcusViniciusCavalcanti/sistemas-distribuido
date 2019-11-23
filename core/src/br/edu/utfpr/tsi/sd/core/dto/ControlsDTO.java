package br.edu.utfpr.tsi.sd.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class ControlsDTO implements Dto {

    @JsonProperty("forward") private final boolean forward;
    @JsonProperty("left") private final boolean left;
    @JsonProperty("right") private final  boolean right;
    @JsonProperty("shoot") private final  boolean shoot;

}
