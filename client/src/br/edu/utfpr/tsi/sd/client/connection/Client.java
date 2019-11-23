package br.edu.utfpr.tsi.sd.client.connection;

import br.edu.utfpr.tsi.sd.core.web.impl.*;

import java.util.function.Consumer;

public interface Client {
    void connect(PlayerDto playerDto);
    void onConnected(Consumer<IntroductoryStateDto> handler);
    void onOtherPlayerConnected(Consumer<PlayerDto> handler);
    void onOtherPlayerDisconnected(Consumer<UuidDto> handler);
    void onGameStateReceived(Consumer<GameStateDto> handler);
    void sendControls(ControlsDto controlsDto);
    void lockEventListeners();
    void unlockEventListeners();
    boolean isConnected();
}
