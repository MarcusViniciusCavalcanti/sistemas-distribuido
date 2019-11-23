package br.edu.utfpr.tsi.sd.core.dto.mapper;

import br.edu.utfpr.tsi.sd.core.controller.RemoteControl;
import br.edu.utfpr.tsi.sd.core.dto.ControlsDTO;

public class ControlsMapper {
    public static void setRemoteControlsByDto(ControlsDTO dto, RemoteControl controls) {
        controls.forward(dto.isForward());
        controls.left(dto.isLeft());
        controls.right(dto.isRight());
        controls.shoot(dto.isShoot());
    }
}
