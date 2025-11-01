package fr.loudo.timberhearth.events;

import fr.loudo.timberhearth.TimberHearth;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(TimberHearth.MOD_ID)
public class ClientTickEventNeoForge {

    public ClientTickEventNeoForge(IEventBus eventBus) {
        NeoForge.EVENT_BUS.addListener(ClientTickEventNeoForge::onClientTick);
    }

    public static void onClientTick(ClientTickEvent.Post event) {
        OnClientTick.tick(Minecraft.getInstance());
    }
}
