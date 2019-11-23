package br.edu.utfpr.tsi.sd.server;

import br.edu.utfpr.tsi.sd.core.container.BulletsContainer;
import br.edu.utfpr.tsi.sd.core.container.PlayersContainer;
import br.edu.utfpr.tsi.sd.core.dto.mapper.ControlsMapper;
import br.edu.utfpr.tsi.sd.core.dto.mapper.GameStateMapper;
import br.edu.utfpr.tsi.sd.core.dto.mapper.PlayerMapper;
import br.edu.utfpr.tsi.sd.core.manager.Collider;
import br.edu.utfpr.tsi.sd.core.manager.Respawner;
import br.edu.utfpr.tsi.sd.core.model.Arena;
import br.edu.utfpr.tsi.sd.core.model.RemotePlayer;
import br.edu.utfpr.tsi.sd.server.connection.Server;
import com.badlogic.gdx.ScreenAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class AsteroidsServerScreen extends ScreenAdapter {
    private final Server server;
    private final PlayersContainer<RemotePlayer> playersContainer;
    private final BulletsContainer bulletsContainer;
    private final Arena arena;
    private final Respawner respawner;
    private final Collider collider;

    @Override
    public void show() {
        server.onPlayerConnected(playerDto -> {
            var connected = PlayerMapper.remotePlayerFromDto(playerDto);
            var connectedDto = PlayerMapper.fromPlayer(connected);
            var gameStateDto = GameStateMapper.fromState(playersContainer, bulletsContainer);

            respawner.respawnFor(connected);

            server.sendIntroductoryDataToConnected(connectedDto, gameStateDto);
            server.notifyOtherPlayersAboutConnected(connectedDto);
            playersContainer.add(connected);
        });

        server.onPlayerDisconnected(id -> {
            playersContainer.removeById(id);
            bulletsContainer.removeByPlayerId(id);
        });

        server.onPlayerSentControls((id, controlsDto) -> playersContainer
                .getById(id)
                .ifPresent(sender -> ControlsMapper.setRemoteControlsByDto(controlsDto, sender.getRemoteControls())));

        server.start();
    }

    @Override
    public void render(float delta) {
        respawner.respawn();
        collider.checkCollisions();

        playersContainer.update(delta);
        playersContainer.streamShips()
                .forEach(arena::ensurePlacementWithinBounds);
        playersContainer.obtainAndStreamBullets()
                .forEach(bulletsContainer::add);

        bulletsContainer.update(delta);
        bulletsContainer.stream()
                .forEach(arena::ensurePlacementWithinBounds);

        server.broadcast(GameStateMapper.fromState(playersContainer, bulletsContainer));
    }
}
