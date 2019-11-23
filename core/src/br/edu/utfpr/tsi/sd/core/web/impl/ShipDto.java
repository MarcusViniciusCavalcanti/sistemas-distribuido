package br.edu.utfpr.tsi.sd.core.web.impl;

import br.edu.utfpr.tsi.sd.core.web.dto.Dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@EqualsAndHashCode
@Getter
public class ShipDto implements Dto {
    private final float x;
    private final float y;
    private final float rotation;
    private final float velocityX;
    private final float velocityY;
    private final float rotationVelocity;

    @JsonCreator
    public ShipDto(
            @JsonProperty("x") float x,
            @JsonProperty("y") float y,
            @JsonProperty("rotation") float rotation,
            @JsonProperty("velocityX") float velocityX,
            @JsonProperty("velocityY") float velocityY,
            @JsonProperty("rotationVelocity") float rotationVelocity) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.rotationVelocity = rotationVelocity;
    }
}
