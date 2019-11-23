package br.edu.utfpr.tsi.sd.core.web.impl;

import br.edu.utfpr.tsi.sd.core.web.dto.Dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class IntroductoryStateDto implements Dto {
    private final PlayerDto connected;
    private final GameStateDto gameState;

    @JsonCreator
    public IntroductoryStateDto(
            @JsonProperty("connected") PlayerDto connected,
            @JsonProperty("gameState") GameStateDto gameState) {
        this.connected = connected;
        this.gameState = gameState;
    }
}
