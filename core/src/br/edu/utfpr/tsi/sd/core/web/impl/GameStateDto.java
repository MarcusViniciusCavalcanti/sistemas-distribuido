package br.edu.utfpr.tsi.sd.core.web.impl;

import br.edu.utfpr.tsi.sd.core.web.dto.Dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
public class GameStateDto implements Dto {
    private final List<PlayerDto> players;
    private final List<BulletDto> bullets;

    @JsonCreator
    public GameStateDto(
            @JsonProperty("players") List<PlayerDto> players,
            @JsonProperty("bullets") List<BulletDto> bullets) {
        this.players = players;
        this.bullets = bullets;
    }
}
