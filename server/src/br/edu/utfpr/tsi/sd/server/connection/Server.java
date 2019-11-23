package br.edu.utfpr.tsi.sd.server.connection;

import br.edu.utfpr.tsi.sd.core.dto.ControlsDTO;
import br.edu.utfpr.tsi.sd.core.dto.GameStateDTO;
import br.edu.utfpr.tsi.sd.core.dto.PlayerDTO;

import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface Server {
    void start();
    void onPlayerConnected(Consumer<PlayerDTO> handler);
    void onPlayerDisconnected(Consumer<UUID> handler);
    void onPlayerSentControls(BiConsumer<UUID, ControlsDTO> handler);
    void broadcast(GameStateDTO gameState);
    void sendIntroductoryDataToConnected(PlayerDTO connected, GameStateDTO gameState);
    void notifyOtherPlayersAboutConnected(PlayerDTO connected);
}
