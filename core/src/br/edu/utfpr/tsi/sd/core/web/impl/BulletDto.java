package br.edu.utfpr.tsi.sd.core.web.impl;

import br.edu.utfpr.tsi.sd.core.web.dto.Dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class BulletDto implements Dto {
    private final String id;
    private final float x;
    private final float y;
    private final float rotation;
    private final String shooterId;

    @JsonCreator
    public BulletDto(
            @JsonProperty("id") String id,
            @JsonProperty("x") float x,
            @JsonProperty("y") float y,
            @JsonProperty("rotation") float rotation,
            @JsonProperty("shooterId") String shooterId) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.shooterId = shooterId;
    }
}
