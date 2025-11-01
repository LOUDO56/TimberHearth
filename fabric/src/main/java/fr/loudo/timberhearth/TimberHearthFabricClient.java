package fr.loudo.timberhearth;

import fr.loudo.timberhearth.network.ClientPacketHandlerFabric;
import fr.loudo.timberhearth.registers.ClientEventsRegister;
import net.fabricmc.api.ClientModInitializer;

public class TimberHearthFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientEventsRegister.register();
        ClientPacketHandlerFabric.handle();
    }
}
