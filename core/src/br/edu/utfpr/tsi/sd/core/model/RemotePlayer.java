package br.edu.utfpr.tsi.sd.core.model;

import br.edu.utfpr.tsi.sd.core.controller.RemoteControl;
import com.badlogic.gdx.graphics.Color;

import java.util.UUID;

public class RemotePlayer extends Player {

    private final RemoteControl remoteControl;

    public RemotePlayer(RemoteControl remoteControl, Color color, UUID id) {
        super(remoteControl, color, id);
        this.remoteControl = remoteControl;
    }

    public RemoteControl getRemoteControls() {
        return remoteControl;
    }

}
