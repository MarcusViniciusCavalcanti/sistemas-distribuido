package br.edu.utfpr.tsi.sd.server.connection;

import br.edu.utfpr.tsi.sd.core.web.impl.ControlsDto;
import br.edu.utfpr.tsi.sd.core.web.impl.GameStateDto;
import br.edu.utfpr.tsi.sd.core.web.impl.PlayerDto;

import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface Server {
    void start();
    void onPlayerConnected(Consumer<PlayerDto> handler);
    void onPlayerDisconnected(Consumer<UUID> handler);
    void onPlayerSentControls(BiConsumer<UUID, ControlsDto> handler);
    void broadcast(GameStateDto gameState);
    void sendIntroductoryDataToConnected(PlayerDto connected, GameStateDto gameState);
    void notifyOtherPlayersAboutConnected(PlayerDto connected);
}
