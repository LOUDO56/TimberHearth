package fr.loudo.timberhearth.network;

import fr.loudo.timberhearth.client.TimberHearthSoundControl;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPacketHandlerNeoForge {

    public static void handlePlayTimberHearthSound(
            final PlayTimberHearthSoundS2C packet, final IPayloadContext context) {
        context.enqueueWork(TimberHearthSoundControl::play);
    }

    public static void handleStopTimberHearthSound(
            final StopTimberHearthSoundS2C packet, final IPayloadContext context) {
        context.enqueueWork(TimberHearthSoundControl::stop);
    }

    public static void handleTimberHearthFadeSound(
            final TimberHearthFadeSoundS2C packet, final IPayloadContext context) {
        context.enqueueWork(() -> TimberHearthSoundControl.reset(packet.flag(), packet.soundType()));
    }
}
