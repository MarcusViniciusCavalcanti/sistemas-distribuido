package br.edu.utfpr.tsi.sd.core.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class IntroductoryStateDTO implements Dto {

    @JsonProperty("connected") private final PlayerDTO connected;
    @JsonProperty("gameState") private final GameStateDTO gameState;
}
