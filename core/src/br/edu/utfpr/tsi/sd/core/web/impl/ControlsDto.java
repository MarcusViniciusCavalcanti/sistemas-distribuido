package br.edu.utfpr.tsi.sd.core.web.impl;

import br.edu.utfpr.tsi.sd.core.web.dto.Dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
public class ControlsDto implements Dto {
    private final boolean forward;
    private final boolean left;
    private final boolean right;
    private final boolean shoot;

    @JsonCreator
    public ControlsDto(
            @JsonProperty("forward") boolean forward,
            @JsonProperty("left") boolean left,
            @JsonProperty("right") boolean right,
            @JsonProperty("shoot") boolean shoot) {
        this.forward = forward;
        this.left = left;
        this.right = right;
        this.shoot = shoot;
    }
}
