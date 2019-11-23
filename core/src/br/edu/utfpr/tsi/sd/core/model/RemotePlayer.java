package br.edu.utfpr.tsi.sd.core.model;

import br.edu.utfpr.tsi.sd.core.controller.RemoteControls;
import com.badlogic.gdx.graphics.Color;

import java.util.UUID;

public class RemotePlayer extends Player {
    private final RemoteControls remoteControls;

    public RemotePlayer(UUID id, RemoteControls remoteControls, Color color) {
        super(id, remoteControls, color);
        this.remoteControls = remoteControls;
    }
    public RemoteControls getRemoteControls() {
        return remoteControls;
    }
}

