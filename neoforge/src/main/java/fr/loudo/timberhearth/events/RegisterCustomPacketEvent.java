package fr.loudo.timberhearth.events;

import fr.loudo.timberhearth.TimberHearth;
import fr.loudo.timberhearth.network.ClientPacketHandlerNeoForge;
import fr.loudo.timberhearth.network.PlayTimberHearthSoundS2C;
import fr.loudo.timberhearth.network.TimberHearthFadeSoundS2C;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod(TimberHearth.MOD_ID)
public class RegisterCustomPacketEvent {

    public RegisterCustomPacketEvent(IEventBus modEventBus) {
        modEventBus.addListener(RegisterCustomPacketEvent::register);
    }

    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1").executesOn(HandlerThread.NETWORK);
        registrar.playToClient(
                PlayTimberHearthSoundS2C.TYPE,
                PlayTimberHearthSoundS2C.STREAM_CODEC,
                ClientPacketHandlerNeoForge::handlePlayTimberHearthSound);
        registrar.playToClient(
                TimberHearthFadeSoundS2C.TYPE,
                TimberHearthFadeSoundS2C.STREAM_CODEC,
                ClientPacketHandlerNeoForge::handleTimberHearthFadeSound);
    }
}
