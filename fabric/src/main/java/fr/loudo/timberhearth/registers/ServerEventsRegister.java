package fr.loudo.timberhearth.registers;

import fr.loudo.timberhearth.events.PlayerServerConnection;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class ServerEventsRegister {

    public static void register() {
        ServerPlayConnectionEvents.JOIN.register(PlayerServerConnection::onPlayerJoin);
    }
}
