package fr.loudo.timberhearth.util;

import fr.loudo.timberhearth.network.PlayTimberHearthSoundS2C;
import fr.loudo.timberhearth.network.StopTimberHearthSoundS2C;
import fr.loudo.timberhearth.platform.Services;
import java.util.List;
import net.minecraft.server.level.ServerPlayer;

public class UtilServer {

    public static void updateOSTSeconds(List<ServerPlayer> players, long time) {
        if (UtilCommon.isDayTime(time)) {
            players.forEach(player -> Services.PACKET_SENDER.sendToPlayer(
                    player, new PlayTimberHearthSoundS2C(UtilCommon.getOSTSecondsFromDayTime(time))));
        } else {
            players.forEach(player -> Services.PACKET_SENDER.sendToPlayer(player, new StopTimberHearthSoundS2C()));
        }
    }
}
