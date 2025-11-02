package fr.loudo.timberhearth.events;

import fr.loudo.timberhearth.client.TimberHearthSoundControl;
import fr.loudo.timberhearth.network.PlayTimberHearthSoundS2C;
import fr.loudo.timberhearth.network.TimberHearthFadeSoundS2C;
import fr.loudo.timberhearth.platform.Services;
import fr.loudo.timberhearth.util.UtilCommon;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class OnPlayerServerConnection {
    public static void playerJoin(ServerPlayer player) {
        ServerLevel level = player.level();
        if (UtilCommon.inCave(player.level(), player.blockPosition())) return;
        if (UtilCommon.isDayTime(level.getDayTime()) && !level.isRaining() && !level.isThundering()) {
            double secondsToPlay = UtilCommon.getOSTSecondsFromDayTime(level.getDayTime());
            Services.PACKET_SENDER.sendToPlayer(player, new PlayTimberHearthSoundS2C(secondsToPlay));
            Services.PACKET_SENDER.sendToPlayer(
                    player, new TimberHearthFadeSoundS2C(false, TimberHearthSoundControl.SoundType.JOIN));
        }
    }
}
