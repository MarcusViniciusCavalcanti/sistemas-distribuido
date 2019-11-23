package br.edu.utfpr.tsi.sd.core.web.mapper;

import br.edu.utfpr.tsi.sd.core.controller.Controls;
import br.edu.utfpr.tsi.sd.core.controller.RemoteControls;
import br.edu.utfpr.tsi.sd.core.web.impl.ControlsDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ControlsMapper {
    public static void setRemoteControlsByDto(ControlsDto dto, RemoteControls controls) {
        controls.forward(dto.isForward());
        controls.left(dto.isLeft());
        controls.right(dto.isRight());
        controls.shoot(dto.isShoot());
    }

    public static ControlsDto mapToDto(Controls controls) {
        return new ControlsDto(
                controls.forward(),
                controls.left(),
                controls.right(),
                controls.shoot()
        );
    }
}
