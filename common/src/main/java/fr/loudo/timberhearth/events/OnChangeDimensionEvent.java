package fr.loudo.timberhearth.events;

import fr.loudo.timberhearth.client.TimberHearthSoundControl;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class OnChangeDimensionEvent {

    public static void changeDimensionEvent(ResourceKey<Level> resourceKey) {
        if (resourceKey == Level.OVERWORLD) {
            TimberHearthSoundControl.reset(true, TimberHearthSoundControl.SoundType.JOIN);
        } else {
            TimberHearthSoundControl.stop();
        }
    }
}
