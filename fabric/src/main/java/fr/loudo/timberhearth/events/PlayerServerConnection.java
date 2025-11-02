package fr.loudo.timberhearth.events;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public class PlayerServerConnection {
    public static void onPlayerJoin(
            ServerGamePacketListenerImpl serverGamePacketListener,
            PacketSender packetSender,
            MinecraftServer minecraftServer) {
        OnPlayerServerConnection.playerJoin(serverGamePacketListener.player);
    }
}
