package fr.loudo.timberhearth.network;

import fr.loudo.timberhearth.client.TimberHearthSoundControl;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ClientPacketHandlerFabric {

    public static void handle() {
        ClientPlayNetworking.registerGlobalReceiver(
                PlayTimberHearthSoundS2C.TYPE, (payload, context) -> TimberHearthSoundControl.play());
        ClientPlayNetworking.registerGlobalReceiver(
                TimberHearthFadeSoundS2C.TYPE,
                (payload, context) -> TimberHearthSoundControl.reset(payload.flag(), payload.soundType()));
    }
}
