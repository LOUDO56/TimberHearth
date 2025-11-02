package fr.loudo.timberhearth.events;

import fr.loudo.timberhearth.TimberHearth;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@Mod(TimberHearth.MOD_ID)
public class PlayerServerConnection {

    public PlayerServerConnection(IEventBus eventBus) {
        NeoForge.EVENT_BUS.addListener(PlayerServerConnection::onPlayerJoin);
    }

    private static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        OnPlayerServerConnection.playerJoin((ServerPlayer) event.getEntity());
    }
}
