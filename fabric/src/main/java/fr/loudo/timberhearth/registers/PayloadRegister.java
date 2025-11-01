package fr.loudo.timberhearth.registers;

import fr.loudo.timberhearth.network.PlayTimberHearthSoundS2C;
import fr.loudo.timberhearth.network.TimberHearthFadeSoundS2C;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class PayloadRegister {

    public static void register() {
        PayloadTypeRegistry.playS2C().register(PlayTimberHearthSoundS2C.TYPE, PlayTimberHearthSoundS2C.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(TimberHearthFadeSoundS2C.TYPE, TimberHearthFadeSoundS2C.STREAM_CODEC);
    }
}
