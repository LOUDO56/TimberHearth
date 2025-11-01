package fr.loudo.timberhearth.registers;

import fr.loudo.timberhearth.events.OnClientTick;
import fr.loudo.timberhearth.events.OnRenderWorld;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class ClientEventsRegister {
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(OnClientTick::tick);
        WorldRenderEvents.LAST.register(worldRenderContext ->
                OnRenderWorld.renderWorld(worldRenderContext.tickCounter().getGameTimeDeltaPartialTick(true)));
    }
}
