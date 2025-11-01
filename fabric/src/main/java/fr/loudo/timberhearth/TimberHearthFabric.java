package fr.loudo.timberhearth;

import fr.loudo.timberhearth.network.ClientPacketHandlerFabric;
import fr.loudo.timberhearth.registers.EventsRegister;
import fr.loudo.timberhearth.registers.PayloadRegister;
import net.fabricmc.api.ModInitializer;

public class TimberHearthFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        CommonClass.init();
        EventsRegister.register();
        PayloadRegister.register();
        ClientPacketHandlerFabric.handle();
    }
}
