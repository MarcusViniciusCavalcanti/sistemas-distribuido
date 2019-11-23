package br.edu.utfpr.tsi.sd.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class BulletDTO implements Dto {

    @JsonProperty("id") private final String id;
    @JsonProperty("x") private final float x;
    @JsonProperty("y") private final float y;
    @JsonProperty("rotation") private final float rotation;
    @JsonProperty("shooterId") private final String shooterId;

}
