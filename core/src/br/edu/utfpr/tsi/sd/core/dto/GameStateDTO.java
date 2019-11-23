package br.edu.utfpr.tsi.sd.core.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class GameStateDTO implements Dto {

    @JsonProperty("players") private final List<PlayerDTO> players;
    @JsonProperty("bullets") private final List<BulletDTO> bullets;

}
