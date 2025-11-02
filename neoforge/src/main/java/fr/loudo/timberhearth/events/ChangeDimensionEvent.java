package fr.loudo.timberhearth.events;

import fr.loudo.timberhearth.TimberHearth;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@Mod(TimberHearth.MOD_ID)
public class ChangeDimensionEvent {
    public ChangeDimensionEvent(IEventBus eventBus) {
        NeoForge.EVENT_BUS.addListener(ChangeDimensionEvent::onDimensionChange);
    }

    private static void onDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        OnChangeDimensionEvent.changeDimensionEvent(event.getTo());
    }
}
