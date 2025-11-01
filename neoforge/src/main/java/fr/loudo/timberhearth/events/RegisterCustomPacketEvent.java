package fr.loudo.timberhearth.events;

import fr.loudo.timberhearth.network.PlayTimberHearthSoundS2C;
import fr.loudo.timberhearth.network.TimberHearthFadeSoundS2C;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class RegisterCustomPacketEvent {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(PlayTimberHearthSoundS2C.TYPE, PlayTimberHearthSoundS2C.STREAM_CODEC);
        registrar.playToClient(TimberHearthFadeSoundS2C.TYPE, TimberHearthFadeSoundS2C.STREAM_CODEC);
    }
}
