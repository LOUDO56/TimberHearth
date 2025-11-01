package fr.loudo.timberhearth.events;

import fr.loudo.timberhearth.TimberHearth;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(TimberHearth.MOD_ID)
public class RenderWorldEvent {

    public RenderWorldEvent(IEventBus modBus) {
        NeoForge.EVENT_BUS.addListener(RenderWorldEvent::onWorldRender);
    }

    private static void onWorldRender(RenderLevelStageEvent.AfterLevel event) {
        OnRenderWorld.renderWorld(Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaPartialTick(true));
    }
}
