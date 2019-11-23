package br.edu.utfpr.tsi.sd.core.web.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IndexedControlsDto extends IndexedDto<ControlsDto> {
    @JsonCreator
    public IndexedControlsDto(
            @JsonProperty("dto") ControlsDto dto,
            @JsonProperty("index") long index) {
        super(dto, index);
    }
}
