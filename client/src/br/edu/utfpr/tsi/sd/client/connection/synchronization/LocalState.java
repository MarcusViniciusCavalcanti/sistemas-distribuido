package br.edu.utfpr.tsi.sd.client.connection.synchronization;

import br.edu.utfpr.tsi.sd.core.web.impl.ControlsDto;
import br.edu.utfpr.tsi.sd.core.web.impl.GameStateDto;

class LocalState {
    private final long index;
    private final float delta;
    private final ControlsDto controlsDto;
    private final GameStateDto gameStateAfterLoop;

    LocalState(long index, float delta, ControlsDto controlsDto, GameStateDto gameStateAfterLoop) {
        this.index = index;
        this.delta = delta;
        this.controlsDto = controlsDto;
        this.gameStateAfterLoop = gameStateAfterLoop;
    }

    public boolean gameStateMatches(GameStateDto serverState) {
        return gameStateAfterLoop.equals(serverState);
    }

    public long getIndex() {
        return index;
    }

    public float getDelta() {
        return delta;
    }

    public ControlsDto getControlsDto() {
        return controlsDto;
    }
}
