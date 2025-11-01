package fr.loudo.timberhearth.events;

import fr.loudo.timberhearth.client.TimberHearthSoundControl;

public class OnRenderWorld {

    public static void renderWorld(float partialTick) {
        TimberHearthSoundControl.fade(partialTick);
    }
}
