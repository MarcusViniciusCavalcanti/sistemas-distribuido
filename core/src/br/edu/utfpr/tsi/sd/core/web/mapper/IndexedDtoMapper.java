package br.edu.utfpr.tsi.sd.core.web.mapper;

import br.edu.utfpr.tsi.sd.core.web.dto.Dto;
import br.edu.utfpr.tsi.sd.core.web.impl.IndexedDto;

public class IndexedDtoMapper {
    public static <UnderlyingDto extends Dto> IndexedDto<UnderlyingDto>
    wrapWithIndex(UnderlyingDto dto, long index) {
        return new IndexedDto<>(dto, index);
    }
}
