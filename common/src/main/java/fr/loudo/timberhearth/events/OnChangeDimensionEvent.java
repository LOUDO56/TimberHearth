package fr.loudo.timberhearth.events;

import fr.loudo.timberhearth.client.TimberHearthSoundControl;
import fr.loudo.timberhearth.network.PlayTimberHearthSoundS2C;
import fr.loudo.timberhearth.network.StopTimberHearthSoundS2C;
import fr.loudo.timberhearth.network.TimberHearthFadeSoundS2C;
import fr.loudo.timberhearth.platform.Services;
import fr.loudo.timberhearth.util.UtilCommon;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class OnChangeDimensionEvent {

    public static void changeDimensionEvent(ResourceKey<Level> resourceKey, ServerPlayer player) {
        if (resourceKey == Level.OVERWORLD) {
            Services.PACKET_SENDER.sendToPlayer(
                    player,
                    new PlayTimberHearthSoundS2C(
                            UtilCommon.getOSTSecondsFromDayTime(player.level().getDayTime())));
            Services.PACKET_SENDER.sendToPlayer(
                    player, new TimberHearthFadeSoundS2C(true, TimberHearthSoundControl.SoundType.DIMENSION));
        } else {
            Services.PACKET_SENDER.sendToPlayer(player, new StopTimberHearthSoundS2C());
        }
    }
}
